package com.hdfclife.strategy;

public class EndowmentPremiumStrategy implements PremiumStrategy{

    @Override
    public int calculate(int premium) {
        return premium * 108 / 100;
    }
}
