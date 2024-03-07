package com.xz.library.admin.service.impl;

import com.xz.library.admin.entity.RegisterInfo;
import com.xz.library.admin.entity.param.ChangPasswordParam;
import com.xz.library.admin.mapper.UserAccountMapper;
import com.xz.library.admin.service.UserOperationService;
import com.xz.library.admin.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xz
 */
@Service
public class UserOperationServiceImpl implements UserOperationService {
    @Autowired
    private UserAccountMapper baseMapper;

    @Override
    public Boolean perfectInformation(RegisterInfo info) {
        int i = baseMapper.updateById(info);
        return i > 0;
    }

    @Override
    public Boolean changPassword(ChangPasswordParam param) {

        return null;
    }
}
