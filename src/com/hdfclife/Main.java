package com.hdfclife;

import com.hdfclife.config.AppConfig;
import com.hdfclife.factory.PolicyFactory;
import com.hdfclife.model.Policy;
import com.hdfclife.store.PolicyStore;

import java.util.*;

public class Main {
    static void main() {
        System.out.println(AppConfig.INSTANCE.getCompanyName());

        PolicyStore store = new PolicyStore();
        store.addPolicy(PolicyFactory.create("TERM","HDFC-LIFE-1001","Anita Sharma",18500,"Active"));
        store.addPolicy(PolicyFactory.create("ULIP","HDFC-LIFE-1002","Rahul Mehta",42000,"Active"));
        store.addPolicy(PolicyFactory.create("ENDOWMENT","HDFC-LIFE-1003","Priya Nair",27000,"Lapsed"));
        store.addPolicy(PolicyFactory.create("TERM","HDFC-LIFE-1004","Vikram Singh",15200,"Active"));
        store.addPolicy(PolicyFactory.create("ULIP","HDFC-LIFE-1005","Sneha Patel",36000,"Active"));
        store.addPolicy(PolicyFactory.create("ENDOWMENT","HDFC-LIFE-1006","Anita Sharma",22000,"Pending"));


        store.printPolicy();
        store.uniqueCustomers();
        store.getPolicyByNum("HDFC-LIFE-1004");
        store.getSortedPolicyNumbers();
        //store.

//
//        HashSet<String> customerName  = new HashSet<>();
//        for(Policy policy : policyList){
//
//        }

//        HashSet<Policy> policies = policyList<Policy>();
    }
}
