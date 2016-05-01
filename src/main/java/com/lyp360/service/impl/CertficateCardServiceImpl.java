package com.lyp360.service.impl;

import com.lyp360.dao.CertificateCardMapper;
import com.lyp360.entity.CertificateCard;
import com.lyp360.service.ICertificateCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by junhao on 2016/4/30.
 * CertficateCardServiceImpl
 */
@Service
public class CertficateCardServiceImpl implements ICertificateCardService {

    @Autowired
    private CertificateCardMapper certificateCardDao;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return certificateCardDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(CertificateCard record) {
        return certificateCardDao.insert(record);
    }

    @Override
    public int insertSelective(CertificateCard record) {
        return certificateCardDao.insertSelective(record);
    }

    @Override
    public CertificateCard selectByPrimaryKey(Long id) {
        return certificateCardDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(CertificateCard record) {
        return certificateCardDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CertificateCard record) {
        return certificateCardDao.updateByPrimaryKey(record);
    }

    @Override
    public List<CertificateCard> selectCertificateCardList(CertificateCard card) {
        return certificateCardDao.selectCertificateCardList(card);
    }

    @Override
    public List<CertificateCard> findValidCard(CertificateCard card) {
        return certificateCardDao.findValidCard(card);
    }
}
