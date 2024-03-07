package com.xz.library.admin.service;

import com.xz.library.admin.entity.RegisterInfo;
import com.xz.library.admin.entity.param.ChangPasswordParam;

/**
 * @author xz
 */
public interface UserOperationService {
    /**
     * 完善用户信息
     *
     * @param info
     * @return
     */
    Boolean perfectInformation(RegisterInfo info);

    /**
     * 修改密码
     *
     * @param param
     * @return
     */
    Boolean changPassword(ChangPasswordParam param);

}
