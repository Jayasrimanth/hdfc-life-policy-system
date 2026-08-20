package com.hdfclife.strategy;

public class UlipPremiumStrategy implements PremiumStrategy{


    @Override
    public int calculate(int premium) {
        return premium * 112 / 100;
    }
}
