package com.lyp360.service;

import com.lyp360.entity.CertificateCard;

import java.util.List;

/**
 * Created by junhao on 2016/4/30.
 * ICertificateCardService
 */
public interface ICertificateCardService {
    int deleteByPrimaryKey(Long id);
    int insert(CertificateCard record);
    int insertSelective(CertificateCard record);
    CertificateCard selectByPrimaryKey(Long id);
    int updateByPrimaryKeySelective(CertificateCard record);
    int updateByPrimaryKey(CertificateCard record);
    List<CertificateCard> selectCertificateCardList(CertificateCard card);
    List<CertificateCard> findValidCard(CertificateCard card);
}
