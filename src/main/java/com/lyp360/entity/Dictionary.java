package com.lyp360.entity;

import java.util.Date;

public class Dictionary {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dictionary.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dictionary.parentId
     *
     * @mbggenerated
     */
    private Long parentId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dictionary.name
     *
     * @mbggenerated
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dictionary.mark
     *
     * @mbggenerated
     */
    private String mark;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dictionary.status
     *
     * @mbggenerated
     */
    private String status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dictionary.createTime
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dictionary.createUserId
     *
     * @mbggenerated
     */
    private Long createUserId;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dictionary
     *
     * @mbggenerated
     */
    public Dictionary(Long id, Long parentId, String name, String mark, String status, Date createTime, Long createUserId) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.mark = mark;
        this.status = status;
        this.createTime = createTime;
        this.createUserId = createUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dictionary
     *
     * @mbggenerated
     */
    public Dictionary() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dictionary.id
     *
     * @return the value of dictionary.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dictionary.id
     *
     * @param id the value for dictionary.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dictionary.parentId
     *
     * @return the value of dictionary.parentId
     *
     * @mbggenerated
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dictionary.parentId
     *
     * @param parentId the value for dictionary.parentId
     *
     * @mbggenerated
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dictionary.name
     *
     * @return the value of dictionary.name
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dictionary.name
     *
     * @param name the value for dictionary.name
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dictionary.mark
     *
     * @return the value of dictionary.mark
     *
     * @mbggenerated
     */
    public String getMark() {
        return mark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dictionary.mark
     *
     * @param mark the value for dictionary.mark
     *
     * @mbggenerated
     */
    public void setMark(String mark) {
        this.mark = mark == null ? null : mark.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dictionary.status
     *
     * @return the value of dictionary.status
     *
     * @mbggenerated
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dictionary.status
     *
     * @param status the value for dictionary.status
     *
     * @mbggenerated
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dictionary.createTime
     *
     * @return the value of dictionary.createTime
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dictionary.createTime
     *
     * @param createTime the value for dictionary.createTime
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dictionary.createUserId
     *
     * @return the value of dictionary.createUserId
     *
     * @mbggenerated
     */
    public Long getCreateUserId() {
        return createUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dictionary.createUserId
     *
     * @param createUserId the value for dictionary.createUserId
     *
     * @mbggenerated
     */
    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }
}