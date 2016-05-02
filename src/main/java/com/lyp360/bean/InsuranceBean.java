package com.lyp360.bean;

import com.lyp360.entity.Insurance;
import com.lyp360.entity.InsuranceAttach;

import java.util.List;

/**
 * Created by junhao on 2016/5/2.
 * InsuranceBean
 */
public class InsuranceBean  {
    private Insurance insurance;

    private List<InsuranceAttach> attachs;

    public Insurance getInsurance() {
        return insurance;
    }

    public void setInsurance(Insurance insurance) {
        this.insurance = insurance;
    }

    public List<InsuranceAttach> getAttachs() {
        return attachs;
    }

    public void setAttachs(List<InsuranceAttach> attachs) {
        this.attachs = attachs;
    }
}
