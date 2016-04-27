package com.lyp360.service.impl;

import com.lyp360.dao.SystemRoleMapper;
import com.lyp360.entity.SystemRole;
import com.lyp360.service.ISystemRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by junhao on 2016/4/27.
 */
@Service
public class SystemRoleServiceImpl implements ISystemRoleService{

    @Autowired
    private SystemRoleMapper roleDao;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return roleDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(SystemRole record) {
        return roleDao.insert(record);
    }

    @Override
    public int insertSelective(SystemRole record) {
        return roleDao.insertSelective(record);
    }

    @Override
    public SystemRole selectByPrimaryKey(Long id) {
        return roleDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(SystemRole record) {
        return roleDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(SystemRole record) {
        return roleDao.updateByPrimaryKey(record);
    }

    @Override
    public List<SystemRole> getRolesByUserId(Long userId) {
        return roleDao.getRolesByUserId(userId);
    }
}
