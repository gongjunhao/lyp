package com.lyp360.service.impl;

import com.lyp360.dao.InsuranceAttachMapper;
import com.lyp360.entity.InsuranceAttach;
import com.lyp360.service.IInsuranceAttachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by junhao on 2016/5/2.
 * InsuranceAttachServiceImpl
 */
@Service
public class InsuranceAttachServiceImpl implements IInsuranceAttachService {

    @Autowired
    private InsuranceAttachMapper insuranceAttachDao;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return insuranceAttachDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(InsuranceAttach record) {
        return insuranceAttachDao.insert(record);
    }

    @Override
    public int insertSelective(InsuranceAttach record) {
        return insuranceAttachDao.insertSelective(record);
    }

    @Override
    public InsuranceAttach selectByPrimaryKey(Long id) {
        return insuranceAttachDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(InsuranceAttach record) {
        return insuranceAttachDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(InsuranceAttach record) {
        return insuranceAttachDao.updateByPrimaryKey(record);
    }

    @Override
    public List<InsuranceAttach> selectAttachesByInsuranceId(Long insuranceId) {
        return insuranceAttachDao.selectAttachesByInsuranceId(insuranceId);
    }
}
