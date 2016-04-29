package com.lyp360.service;

import com.lyp360.entity.SystemRole;
import com.lyp360.entity.SystemUser;

import java.util.List;

/**
 * Created by junhao on 2016/4/27.
 * 角色接口
 */
public interface ISystemRoleService {
    int deleteByPrimaryKey(Long id);
    int insert(SystemRole record);
    int insertSelective(SystemRole record);
    SystemRole selectByPrimaryKey(Long id);
    int updateByPrimaryKeySelective(SystemRole record);
    int updateByPrimaryKey(SystemRole record);
    List<SystemRole> getRolesByUserId(Long userId);

    List<SystemRole> selectRoleList(SystemRole role);

    SystemRole findRoleByRoleCode(String roleCode);
}
