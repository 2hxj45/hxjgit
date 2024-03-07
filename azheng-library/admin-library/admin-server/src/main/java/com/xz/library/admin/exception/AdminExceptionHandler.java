package com.xz.library.admin.exception;

import com.xz.library.admin.bean.LoginFailureException;
import com.xz.library.admin.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

/**
 * 全局错误处理
 *
 * @author xz
 */
@RestControllerAdvice
@Slf4j
public class AdminExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler({LoginFailureException.class})
    public Result handlerLoginFailureException(LoginFailureException ex) {
        log.error("登录失效{}", ex.getMessage());
        Result result = new Result();
        result.setCode(Result.CODE_LOGINFAILURE);
        result.setMsg("登录失效");
        return result;
    }

    @ExceptionHandler({RuntimeException.class})
    public Result handleGssException(RuntimeException ex) {
        log.error("全局异常处理，记录到发生RuntimeException", ex);
        return Result.err(ex.getMessage());
    }

    /**
     * 处理@Validate RequestParam的请求入参校验
     *
     * @param exception
     * @param request
     * @param response
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public Result processException(ConstraintViolationException exception, HttpServletRequest request, HttpServletResponse response) {
        Set<ConstraintViolation<?>> constraintViolations = exception.getConstraintViolations();
        if (constraintViolations != null && constraintViolations.size() > 0) {
            for (ConstraintViolation<?> error : constraintViolations) {
                String message = error.getMessage();
                return Result.err(message);
            }
        }
        return Result.err("参数错误:" + exception.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Object exceptionHandler(Exception e) {
        if (e instanceof ExceptionCode) {
            ExceptionCode exceptionCode = (ExceptionCode) e;
            ErrorCode errorCode = exceptionCode.getCode();
            log.error("报错，code:{}, msg:{}", errorCode.getCode(), errorCode.getMsg(), e);
            return Result.err(errorCode.getCode(), errorCode.getMsg());
        }
        log.error("未知错误：", e);
        return Result.err(e.getMessage());
    }

    /**
     * 拦截 @RequestBody Validator校验  异常
     *
     * @param ex      异常信息
     * @param headers 请求头信息
     * @param status  状态码
     * @param request 请求
     * @return
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error("全局异常处理，【参数校验】异常 MethodArgumentNotValidException", ex);
        // 绑定异常结果
        BindingResult bindingResult = ex.getBindingResult();
        return handleBindResult(bindingResult.hasErrors(), bindingResult.getFieldError());
    }

    /**
     * 拦截 @RequestParam Validator校验异常
     *
     * @param ex      异常信息，继承自BindResult，所以可以拿到参数错误信息
     * @param headers 请求头信息
     * @param status  状态码
     * @param request 请求
     * @return
     */
    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleBindResult(ex.hasErrors(), ex.getFieldError());
    }

    private ResponseEntity<Object> handleBindResult(boolean b, FieldError fieldError2) {
        if (b) {
            // 参数有错
            FieldError fieldError = fieldError2;
            if (fieldError != null) {
                String field = fieldError.getField();
                String defaultMessage = fieldError.getDefaultMessage();
                log.warn("Validator参数校验异常，【{}（{}）】", field, defaultMessage);
                return new ResponseEntity<>(Result.err(defaultMessage), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(Result.err("参数异常"), HttpStatus.OK);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String msg = null;
        msg = "参数名为：" + ex.getPropertyName() + "需要的类型为：" + ex.getRequiredType() + "当前值为：" + ex.getValue();
        return get400ErrorResult(msg);
    }

    @Override
    protected ResponseEntity<Object> handleConversionNotSupported(ConversionNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String msg = null;
        try {
            msg = ex.getCause().getMessage();
        } catch (Exception e) {
            msg = ex.getMessage();
        }
        return get400ErrorResult(msg);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String msg = null;
        try {
            msg = ex.getCause().getMessage();
        } catch (Exception e) {
            msg = ex.getMessage();
        }
        return get400ErrorResult(msg);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String msg = "参数名为：" + ex.getParameterName() + " " + ex.getParameterType();
        return get400ErrorResult(msg);
    }

    @Override
    protected ResponseEntity<Object> handleServletRequestBindingException(org.springframework.web.bind.ServletRequestBindingException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String msg = null;
        try {
            msg = ex.getCause().getMessage();
        } catch (Exception e) {
            msg = ex.getMessage();
        }
        return get400ErrorResult(msg);
    }

    /**
     * 400 错误的时候 返回这样的结果
     *
     * @param msg 返回信息
     * @return
     */
    private ResponseEntity<Object> get400ErrorResult(String msg) {
        Result<String> result = new Result<>();
        result.setCode(Result.CODE_ERROR);
        result.setMsg("请求失败，传输参数格式有误");
        String data = HttpStatus.BAD_REQUEST.toString() + " " + msg;
        result.setData(data);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
