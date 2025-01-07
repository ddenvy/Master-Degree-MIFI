package com.finance.model;

/**
 * Класс для расходов.
 */
public class Expense extends Transaction {
    public Expense(String category, double amount) {
        super(category, amount);
    }

    @Override
    public void apply(Wallet wallet) {
        wallet.addExpense(getCategory(), getAmount());
    }
}