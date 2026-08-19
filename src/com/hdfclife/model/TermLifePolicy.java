package com.hdfclife.model;

public class TermLifePolicy extends Policy{

    public TermLifePolicy(String policyNo, String customer,int premium, String status){
        super("TERM", policyNo,customer, premium, status);

    }
}
