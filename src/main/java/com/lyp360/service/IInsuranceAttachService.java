package com.lyp360.service;

import com.lyp360.entity.InsuranceAttach;

import java.util.List;

/**
 * Created by junhao on 2016/5/2.
 */
public interface IInsuranceAttachService {
    int deleteByPrimaryKey(Long id);
    int insert(InsuranceAttach record);
    int insertSelective(InsuranceAttach record);
    InsuranceAttach selectByPrimaryKey(Long id);
    int updateByPrimaryKeySelective(InsuranceAttach record);
    int updateByPrimaryKey(InsuranceAttach record);
    List<InsuranceAttach> selectAttachesByInsuranceId(Long insuranceId);
}
