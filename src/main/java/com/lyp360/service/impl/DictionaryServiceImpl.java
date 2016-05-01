package com.lyp360.service.impl;

import com.lyp360.dao.DictionaryMapper;
import com.lyp360.entity.Dictionary;
import com.lyp360.service.IDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by junhao on 2016/4/29.
 * DictionaryServiceImpl
 */
@Service
public class DictionaryServiceImpl implements IDictionaryService {

    @Autowired
    private DictionaryMapper dictionaryDao;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return dictionaryDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Dictionary record) {
        return dictionaryDao.insert(record);
    }

    @Override
    public int insertSelective(Dictionary record) {
        return dictionaryDao.insertSelective(record);
    }

    @Override
    public Dictionary selectByPrimaryKey(Long id) {
        return dictionaryDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Dictionary record) {
        return dictionaryDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Dictionary record) {
        return dictionaryDao.updateByPrimaryKey(record);
    }

    @Override
    public List<Dictionary> getChildNodes(Long parentId) {
        return dictionaryDao.getChildNodes(parentId);
    }

    @Override
    public List<Dictionary> selectDictionaryList(Dictionary dictionary) {
        return dictionaryDao.selectDictionaryList(dictionary);
    }

    @Override
    public Dictionary findDictByCode(String code) {
        return dictionaryDao.findDictByCode(code);
    }
}
