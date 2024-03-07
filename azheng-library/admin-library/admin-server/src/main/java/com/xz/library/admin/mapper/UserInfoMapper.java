package com.xz.library.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xz.library.admin.entity.LoginUser;
import com.xz.library.admin.entity.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author xz
 */
public interface UserInfoMapper extends BaseMapper<UserInfo> {
    /**
     * 查出userId,用户名和密码
     *
     * @param userName
     * @param password
     * @return
     */
    @Select("SELECT " +
            " ui.id AS userId, " +
            " ui.user_name AS userName," +
            " ui.`password` AS password" +
            " FROM " +
            " user_account ui " +
            " WHERE " +
            " ui.user_name = #{userName} " +
            " AND " +
            " ui.`password` = #{password} ")
    LoginUser getLoginUser(@Param("userName") String userName, @Param("password") String password);
}
