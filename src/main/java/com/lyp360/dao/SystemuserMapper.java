package com.lyp360.dao;

import com.lyp360.entity.Systemuser;

public interface SystemuserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table systemuser
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table systemuser
     *
     * @mbggenerated
     */
    int insert(Systemuser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table systemuser
     *
     * @mbggenerated
     */
    int insertSelective(Systemuser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table systemuser
     *
     * @mbggenerated
     */
    Systemuser selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table systemuser
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Systemuser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table systemuser
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Systemuser record);
}