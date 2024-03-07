package com.xz.library.admin.mapper;

import com.xz.library.admin.entity.RegisterInfo;

/**
 * @author xz
 */
public interface UserOperationMapper {
    /**
     * 完善用户信息
     *
     * @param info
     * @return
     */
    Boolean perfectInformation(RegisterInfo info);
}
