package com.hdfclife.store;

import com.hdfclife.exception.PolicyNotFoundException;
import com.hdfclife.model.Policy;

import java.util.*;

public class PolicyStore {

    private final List<Policy> allPolicy = new ArrayList<>();
    private final Set<String> uniqueCustomer = new HashSet<>();
    private final Map<String, Policy> policyLookup = new HashMap<>();
    private final Map<String, Policy> sortedPolicy = new TreeMap<>();

    public void addPolicy(Policy policy){
        allPolicy.add(policy);
        uniqueCustomer.add(policy.getCustomer());
        policyLookup.put(policy.getPolicyNo(), policy);
        sortedPolicy.put(policy.getPolicyNo(), policy);
    }

    public Policy getPolicyByNum(String policyNo){
        Policy policy = policyLookup.get(policyNo);
        if(policy == null){
            throw new PolicyNotFoundException("Policy not found exception " + policyNo);
        }
        return policy;
    }

    public void printPolicy(){
        Iterator<Policy> policyIterator = new Iterator<>(){
            private int index = 0;
            @Override
            public boolean hasNext() {
                return index < allPolicy.size();
            }

            @Override
            public Policy next() {
                if(!hasNext()) throw new NoSuchElementException("No more polies left");
                return allPolicy.get(index++);
            }
        };

        while (policyIterator.hasNext()){
            Policy policy = policyIterator.next();
            System.out.println(policy);
        }
    }

    public void uniqueCustomers(){
        System.out.println("The number of unique "+uniqueCustomer.toArray().length);
    }

    public void getSortedPolicyNumbers(){
        System.out.println(sortedPolicy.keySet());
    }

}
