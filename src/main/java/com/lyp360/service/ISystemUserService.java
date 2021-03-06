package com.lyp360.service;

import com.lyp360.entity.SystemUser;

import java.util.List;

/**
 * Created by GongJunhao on 2016/4/25.
 * ISystemUserService
 */
public interface ISystemUserService {
    int deleteByPrimaryKey(Long id);
    int insert(SystemUser record);
    int insertSelective(SystemUser record);
    SystemUser selectByPrimaryKey(Long id);
    int updateByPrimaryKeySelective(SystemUser record);
    int updateByPrimaryKey(SystemUser record);
    List<SystemUser> selectUserList(SystemUser systemuser);
    SystemUser findUserByLoginName(String loginName);
}
