package com.hdfclife.observer;

import com.hdfclife.model.Claim;

public class BranchLetterNotifier implements ClaimObserver{
    @Override
    public void onClaimUpdate(Claim claim) {
        System.out.println("[BRANCH LETTER NOTIFICATION] Claim for Policy " + claim.getPolicyNo() + " updated to " + claim.getStatus());
    }
}
