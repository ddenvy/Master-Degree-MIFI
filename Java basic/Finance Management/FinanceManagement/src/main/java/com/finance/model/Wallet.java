package com.finance.model;

import java.util.HashMap;
import java.util.Map;

public class Wallet {
    private double balance;
    private Map<String, Double> incomes;
    private Map<String, Double> expenses;
    private Map<String, Double> budgets;

    public Wallet() {
        this.balance = 0.0;
        this.incomes = new HashMap<>();
        this.expenses = new HashMap<>();
        this.budgets = new HashMap<>();
    }

    public void addIncome(String category, double amount) {
        incomes.put(category, incomes.getOrDefault(category, 0.0) + amount);
        balance += amount;
    }

    public void addExpense(String category, double amount) {
        expenses.put(category, expenses.getOrDefault(category, 0.0) + amount);
        balance -= amount;
    }

    public void setBudget(String category, double amount) {
        budgets.put(category, amount);
    }

    public double getRemainingBudget(String category) {
        return budgets.getOrDefault(category, 0.0) - expenses.getOrDefault(category, 0.0);
    }

    public double getBalance() {
        return balance;
    }

    public Map<String, Double> getIncomes() {
        return incomes;
    }

    public Map<String, Double> getExpenses() {
        return expenses;
    }

    public Map<String, Double> getBudgets() {
        return budgets;
    }
}