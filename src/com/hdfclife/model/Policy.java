package com.hdfclife.model;

public abstract class Policy {
    private String policyNo;
    private String customer;
    private String type;
    private Integer premium;
    private String status;

    public Policy() {
    }

    public Policy(String type,String policyNo, String customer, Integer premium, String status) {
        this.policyNo = policyNo;
        this.customer = customer;
        this.type = type;
        this.premium = premium;
        this.status = status;
    }

    public String getPolicyNo() {
        return policyNo;
    }

    public void setPolicyNo(String policyNo) {
        this.policyNo = policyNo;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getPremium() {
        return premium;
    }

    public void setPremium(Integer premium) {
        this.premium = premium;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return policyNo + " | " +customer + " | " +type + " | "+ premium + " | "+ status ;
    }
}
