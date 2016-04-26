package com.lyp360.service.impl;


import com.lyp360.dao.SystemuserMapper;
import com.lyp360.entity.Systemuser;
import com.lyp360.service.ISystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by GongJunhao on 2016/4/25.
 *
 * SystemUserServiceImpl
 */
@Service
public class SystemUserServiceImpl implements ISystemUserService {

    @Autowired
    private SystemuserMapper systemuserMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return systemuserMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Systemuser record) {
        return systemuserMapper.insert(record);
    }

    @Override
    public int insertSelective(Systemuser record) {
        return systemuserMapper.insertSelective(record);
    }

    @Override
    public Systemuser selectByPrimaryKey(Long id) {
        return systemuserMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Systemuser record) {
        return systemuserMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Systemuser record) {
        return systemuserMapper.updateByPrimaryKey(record);
    }

    @Override
    public Systemuser findUserByLoginname(String username) {
        return systemuserMapper.findUserByLoginname(username);
    }
}
