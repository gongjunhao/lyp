package com.lyp360.dao;

import com.lyp360.entity.Dictionary;

public interface DictionaryMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Dictionary
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Dictionary
     *
     * @mbggenerated
     */
    int insert(Dictionary record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Dictionary
     *
     * @mbggenerated
     */
    int insertSelective(Dictionary record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Dictionary
     *
     * @mbggenerated
     */
    Dictionary selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Dictionary
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Dictionary record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Dictionary
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Dictionary record);
}