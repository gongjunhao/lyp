package com.lyp360.service.impl;

import com.lyp360.dao.InsuranceMapper;
import com.lyp360.entity.Insurance;
import com.lyp360.service.IInsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by junhao on 2016/4/30.
 * InsuranceServiceImpl
 */
@Service
public class InsuranceServiceImpl implements IInsuranceService {

    @Autowired
    private InsuranceMapper insuranceDao;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return insuranceDao.deleteByPrimaryKey(id);
    }

    @Override
    public long insert(Insurance record) {
        int count = insuranceDao.insert(record);
        return record.getId();
    }

    @Override
    public int insertSelective(Insurance record) {
        return insuranceDao.insertSelective(record);
    }

    @Override
    public Insurance selectByPrimaryKey(Long id) {
        return insuranceDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Insurance record) {
        return insuranceDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Insurance record) {
        return insuranceDao.updateByPrimaryKey(record);
    }

    @Override
    public List<Insurance> selectInsuranceList(Insurance record) {
        return insuranceDao.selectInsuranceList(record);
    }

    @Override
    public List<Insurance> selectInsuranceListByTime(Insurance insurance, Date startDate, Date endDate) {
        insurance.setStartTime(startDate);
        insurance.setEndTime(endDate);
        return insuranceDao.selectInsuranceListByTime(insurance);
    }
}
