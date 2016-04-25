package com.lyp360.service;

/**
 * Created by GongJunhao on 2016/4/25.
 * ISystemUserService
 */
public interface ISystemUserService {
    int deleteByPrimaryKey(Long id);
    int insert(Systemuser record);
    int insertSelective(Systemuser record);
    Systemuser selectByPrimaryKey(Long id);
    int updateByPrimaryKeySelective(Systemuser record);
    int updateByPrimaryKey(Systemuser record);
}
