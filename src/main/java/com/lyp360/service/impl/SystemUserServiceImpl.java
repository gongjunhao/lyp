package com.lyp360.service.impl;


import com.lyp360.dao.SystemUserMapper;
import com.lyp360.entity.SystemUser;
import com.lyp360.service.ISystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by GongJunhao on 2016/4/25.
 *
 * SystemUserServiceImpl
 */
@Service
public class SystemUserServiceImpl implements ISystemUserService {

    @Autowired
    private SystemUserMapper systemuserMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return systemuserMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(SystemUser record) {
        return systemuserMapper.insert(record);
    }

    @Override
    public int insertSelective(SystemUser record) {
        return systemuserMapper.insertSelective(record);
    }

    @Override
    public SystemUser selectByPrimaryKey(Long id) {
        return systemuserMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(SystemUser record) {
        return systemuserMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(SystemUser record) {
        return systemuserMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<SystemUser> selectUserList(SystemUser systemuser) {
        return systemuserMapper.selectUserList(systemuser);
    }

    @Override
    public SystemUser findUserByLoginName(String loginName) {
        return systemuserMapper.findUserByLoginName(loginName);
    }
}
