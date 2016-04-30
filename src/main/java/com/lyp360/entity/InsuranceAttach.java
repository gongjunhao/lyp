package com.lyp360.entity;

public class InsuranceAttach {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column InsuranceAttach.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column InsuranceAttach.insuranceId
     *
     * @mbggenerated
     */
    private Long insuranceId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column InsuranceAttach.attachName
     *
     * @mbggenerated
     */
    private String attachName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column InsuranceAttach.attachSize
     *
     * @mbggenerated
     */
    private String attachSize;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column InsuranceAttach.savePath
     *
     * @mbggenerated
     */
    private String savePath;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table InsuranceAttach
     *
     * @mbggenerated
     */
    public InsuranceAttach(Long id, Long insuranceId, String attachName, String attachSize, String savePath) {
        this.id = id;
        this.insuranceId = insuranceId;
        this.attachName = attachName;
        this.attachSize = attachSize;
        this.savePath = savePath;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table InsuranceAttach
     *
     * @mbggenerated
     */
    public InsuranceAttach() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column InsuranceAttach.id
     *
     * @return the value of InsuranceAttach.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column InsuranceAttach.id
     *
     * @param id the value for InsuranceAttach.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column InsuranceAttach.insuranceId
     *
     * @return the value of InsuranceAttach.insuranceId
     *
     * @mbggenerated
     */
    public Long getInsuranceId() {
        return insuranceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column InsuranceAttach.insuranceId
     *
     * @param insuranceId the value for InsuranceAttach.insuranceId
     *
     * @mbggenerated
     */
    public void setInsuranceId(Long insuranceId) {
        this.insuranceId = insuranceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column InsuranceAttach.attachName
     *
     * @return the value of InsuranceAttach.attachName
     *
     * @mbggenerated
     */
    public String getAttachName() {
        return attachName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column InsuranceAttach.attachName
     *
     * @param attachName the value for InsuranceAttach.attachName
     *
     * @mbggenerated
     */
    public void setAttachName(String attachName) {
        this.attachName = attachName == null ? null : attachName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column InsuranceAttach.attachSize
     *
     * @return the value of InsuranceAttach.attachSize
     *
     * @mbggenerated
     */
    public String getAttachSize() {
        return attachSize;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column InsuranceAttach.attachSize
     *
     * @param attachSize the value for InsuranceAttach.attachSize
     *
     * @mbggenerated
     */
    public void setAttachSize(String attachSize) {
        this.attachSize = attachSize == null ? null : attachSize.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column InsuranceAttach.savePath
     *
     * @return the value of InsuranceAttach.savePath
     *
     * @mbggenerated
     */
    public String getSavePath() {
        return savePath;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column InsuranceAttach.savePath
     *
     * @param savePath the value for InsuranceAttach.savePath
     *
     * @mbggenerated
     */
    public void setSavePath(String savePath) {
        this.savePath = savePath == null ? null : savePath.trim();
    }
}