package com.hdfclife.model;

public class EndowmentPolicy extends Policy{
    public EndowmentPolicy(String policyNo, String customer,int premium, String status){
        super("ENDOWMENT",policyNo,customer, premium, status);
    }
}
