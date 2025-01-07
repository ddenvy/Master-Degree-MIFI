package com.finance.model;

/**
 * Класс для доходов.
 */
public class Income extends Transaction {
    public Income(String category, double amount) {
        super(category, amount);
    }

    @Override
    public void apply(Wallet wallet) {
        wallet.addIncome(getCategory(), getAmount());
    }
}