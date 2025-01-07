package com.finance.model;

/**
 * Абстрактный класс для финансовых операций (доходы и расходы).
 */
public abstract class Transaction {
    private String category;
    private double amount;

    public Transaction(String category, double amount) {
        this.category = category;
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public double getAmount() {
        return amount;
    }

    /**
     * Абстрактный метод для применения операции к кошельку.
     */
    public abstract void apply(Wallet wallet);
}