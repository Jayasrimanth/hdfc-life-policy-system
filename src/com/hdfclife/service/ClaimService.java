package com.hdfclife.service;

import com.hdfclife.config.AppConfig;
import com.hdfclife.exception.InvalidClaimException;
import com.hdfclife.exception.PolicyNotFoundException;
import com.hdfclife.model.Claim;
import com.hdfclife.observer.ClaimEventPublisher;
import com.hdfclife.store.PolicyStore;

public class ClaimService {
    private final PolicyStore policyStore;
    private final ClaimEventPublisher publisher;

    public ClaimService(PolicyStore policyStore, ClaimEventPublisher publisher) {
        this.policyStore = policyStore;
        this.publisher = publisher;
    }

    public void fileClaim(Claim claim) {
        if (!policyStore.getPolicyMap().containsKey(claim.getPolicyNo())) {
            throw new PolicyNotFoundException("Policy not found exception " + claim.getPolicyNo());
        }
        if (claim.getClaimAmount() <= 0 || claim.getClaimAmount() > AppConfig.INSTANCE.getMaxClaimAmount()) {
            throw new InvalidClaimException("Invalid claim amount: " + claim.getClaimAmount());
        }

        try (AuditLogger logger = new AuditLogger("audit.log")) {
            logger.log("Claim filed for policy: " + claim.getPolicyNo() + " Amount: " + claim.getClaimAmount());
        }
    }

    public void updateClaimStatus(Claim claim, String status) {
        claim.updateStatus(status);
        publisher.notifyObservers(claim);
    }
}
