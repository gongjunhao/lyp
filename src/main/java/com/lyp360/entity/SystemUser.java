package com.lyp360.entity;

import java.util.Date;

public class SystemUser {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column systemuser.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column systemuser.loginName
     *
     * @mbggenerated
     */
    private String loginName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column systemuser.nickName
     *
     * @mbggenerated
     */
    private String nickName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column systemuser.passWord
     *
     * @mbggenerated
     */
    private String passWord;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column systemuser.status
     *
     * @mbggenerated
     */
    private String status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column systemuser.createTime
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table systemuser
     *
     * @mbggenerated
     */
    public SystemUser(Long id, String loginName, String nickName, String passWord, String status, Date createTime) {
        this.id = id;
        this.loginName = loginName;
        this.nickName = nickName;
        this.passWord = passWord;
        this.status = status;
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table systemuser
     *
     * @mbggenerated
     */
    public SystemUser() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column systemuser.id
     *
     * @return the value of systemuser.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column systemuser.id
     *
     * @param id the value for systemuser.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column systemuser.loginName
     *
     * @return the value of systemuser.loginName
     *
     * @mbggenerated
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column systemuser.loginName
     *
     * @param loginName the value for systemuser.loginName
     *
     * @mbggenerated
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column systemuser.nickName
     *
     * @return the value of systemuser.nickName
     *
     * @mbggenerated
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column systemuser.nickName
     *
     * @param nickName the value for systemuser.nickName
     *
     * @mbggenerated
     */
    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column systemuser.passWord
     *
     * @return the value of systemuser.passWord
     *
     * @mbggenerated
     */
    public String getPassWord() {
        return passWord;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column systemuser.passWord
     *
     * @param passWord the value for systemuser.passWord
     *
     * @mbggenerated
     */
    public void setPassWord(String passWord) {
        this.passWord = passWord == null ? null : passWord.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column systemuser.status
     *
     * @return the value of systemuser.status
     *
     * @mbggenerated
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column systemuser.status
     *
     * @param status the value for systemuser.status
     *
     * @mbggenerated
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column systemuser.createTime
     *
     * @return the value of systemuser.createTime
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column systemuser.createTime
     *
     * @param createTime the value for systemuser.createTime
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}