package com.lyp360.service;

import com.lyp360.entity.Insurance;

import java.util.Date;
import java.util.List;

/**
 * Created by junhao on 2016/4/30.
 * IInsuranceService
 */
public interface IInsuranceService {
    int deleteByPrimaryKey(Long id);
    long insert(Insurance record);
    int insertSelective(Insurance record);
    Insurance selectByPrimaryKey(Long id);
    int updateByPrimaryKeySelective(Insurance record);
    int updateByPrimaryKey(Insurance record);
    List<Insurance> selectInsuranceList(Insurance record);
    List<Insurance> selectInsuranceListByTime(Insurance insurance, Date startDate, Date endDate);
}
