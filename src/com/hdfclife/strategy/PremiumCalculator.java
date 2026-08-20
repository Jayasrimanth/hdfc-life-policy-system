package com.hdfclife.strategy;

import com.hdfclife.model.Policy;

public class PremiumCalculator {
    private PremiumStrategy strategy;

    public PremiumCalculator() {
    }

    public PremiumCalculator(PremiumStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(PremiumStrategy strategy) {
        this.strategy = strategy;
    }

    public int compute(int premium){
        return strategy.calculate(premium);
    }

}
