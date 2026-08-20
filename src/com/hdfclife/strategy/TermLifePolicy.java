package com.hdfclife.strategy;

public class TermLifePolicy implements PremiumStrategy{

    @Override
    public int calculate(int premium) {
        return premium * 100/100;
    }
}
