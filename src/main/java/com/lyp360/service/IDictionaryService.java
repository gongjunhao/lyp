package com.lyp360.service;

import com.lyp360.entity.Dictionary;
import com.lyp360.entity.SystemUser;

import java.util.List;

/**
 * Created by junhao on 2016/4/29.
 * IDictionaryService
 */
public interface IDictionaryService {
    int deleteByPrimaryKey(Long id);
    int insert(Dictionary record);
    int insertSelective(Dictionary record);
    Dictionary selectByPrimaryKey(Long id);
    int updateByPrimaryKeySelective(Dictionary record);
    int updateByPrimaryKey(Dictionary record);
    List<Dictionary> getChildNodes(Long parentId);
    List<Dictionary> selectDictionaryList(Dictionary dictionary);
    Dictionary findDictByRoleCode(String code);
}
