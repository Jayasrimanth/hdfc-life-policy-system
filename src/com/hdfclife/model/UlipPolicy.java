package com.hdfclife.model;

public class UlipPolicy extends Policy{
    public UlipPolicy(String policyNo, String customer,int premium, String status){
        super("ULIP",policyNo,customer, premium, status);
    }
}
