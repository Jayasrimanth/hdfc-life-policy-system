package com.hdfclife;

import com.hdfclife.config.AppConfig;
import com.hdfclife.exception.PolicyNotFoundException;
import com.hdfclife.exception.UnknownPolicyTypeException;
import com.hdfclife.factory.PolicyFactory;
import com.hdfclife.model.Claim;
import com.hdfclife.model.Policy;
import com.hdfclife.model.Urgency;
import com.hdfclife.observer.BranchLetterNotifier;
import com.hdfclife.observer.ClaimEventPublisher;
import com.hdfclife.observer.InAppNotifier;
import com.hdfclife.service.ClaimService;
import com.hdfclife.store.PolicyStore;
import com.hdfclife.strategy.PremiumCalculator;
import com.hdfclife.strategy.UlipPremiumStrategy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
        PremiumCalculator calculator = new PremiumCalculator();
        calculator.setStrategy(new UlipPremiumStrategy());
        int calculatedPremium = calculator.compute(42000);
        System.out.println("ULIP premium for HDFC-LIFE-1002 -> " + calculatedPremium);

        ClaimEventPublisher publisher = new ClaimEventPublisher();
        publisher.registerObserver(new InAppNotifier());
        publisher.registerObserver(new BranchLetterNotifier());


        // Filing three claims
        List<Claim> claims = Arrays.asList(
                new Claim.Builder("HDFC-LIFE-1001",25000, Urgency.HIGH)
                        .hospitalName("Apollo")
                        .remarks("Hospitalisation")
                        .build(),

                new Claim.Builder("HDFC-LIFE-1002",18000, Urgency.MEDIUM)
                        .hospitalName("Apollo")
                        .remarks("Hospitalisation")
                        .build(),

                new Claim.Builder("HDFC-LIFE-1004",12000, Urgency.LOW)
                        .remarks("Just Matured")
                        .build()
        );

        PolicyStore policyStore = new PolicyStore();
        ClaimEventPublisher claimEventPublisher = new ClaimEventPublisher();

        ClaimService claimService = new ClaimService(policyStore, claimEventPublisher);

        for (Claim claim : claims) {
            claimService.fileClaim(claim);
        }

        System.out.println("\nUpdating HIGH claim to APPROVED: \n");
// Renamed claimUpdate -> updateClaimStatus
        claimService.updateClaimStatus(claims.getFirst(), "APPROVED");

        System.out.println("Priority Queue Poll Order:");
// Helper method now exists on PolicyStore
        PriorityQueue<Claim> priorityQueue = PolicyStore.buildPriorityQueue(claims);

        while (!priorityQueue.isEmpty()) {
            System.out.println(priorityQueue.poll().getUrgency());
        }

// Exception handling call matches your PolicyStore method name
        String policyNo = "HDFC-LIFE-9999";
        try {
            PolicyStore.getPolicyByNum(policyNo);
        } catch (PolicyNotFoundException e) {
            System.out.println("Caught: " + e.getMessage());
        }
        // Checking Exceptional Handling Mechanism for creating an invalid policy
        try {

            PolicyFactory.create("INVALID", "HDFC-LIFE-1010", "A", 40000,"ACTIVE");

        } catch(UnknownPolicyTypeException e) {

            System.out.println("\n" + e.getMessage());
        }

        // Reading a line from Audit.log
        System.out.println("\nA Line from audit.log using BufferedReader:");
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader("audit.log"))){

            System.out.println(bufferedReader.readLine());

        } catch(IOException e) {

            System.out.println("File not Found.");
        }


    }
}
