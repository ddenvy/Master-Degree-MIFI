package com.finance.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс Wallet представляет кошелек пользователя.
 * Он хранит информацию о балансе, доходах, расходах и бюджетах.
 */
public class Wallet {
    private double balance; // Текущий баланс кошелька
    private Map<String, Double> incomes; // Доходы по категориям
    private Map<String, Double> expenses; // Расходы по категориям
    private Map<String, Double> budgets; // Бюджеты по категориям

    /**
     * Конструктор класса Wallet.
     * Инициализирует баланс, доходы, расходы и бюджеты.
     */
    public Wallet() {
        this.balance = 0.0;
        this.incomes = new HashMap<>();
        this.expenses = new HashMap<>();
        this.budgets = new HashMap<>();
    }

    /**
     * Добавляет доход в указанную категорию.
     *
     * @param category Категория дохода (например, "Зарплата").
     * @param amount   Сумма дохода.
     */
    public void addIncome(String category, double amount) {
        incomes.put(category, incomes.getOrDefault(category, 0.0) + amount);
        balance += amount;
    }

    /**
     * Добавляет расход в указанную категорию.
     *
     * @param category Категория расхода (например, "Еда").
     * @param amount   Сумма расхода.
     */
    public void addExpense(String category, double amount) {
        expenses.put(category, expenses.getOrDefault(category, 0.0) + amount);
        balance -= amount;
    }

    /**
     * Устанавливает бюджет для указанной категории.
     *
     * @param category Категория бюджета (например, "Еда").
     * @param amount   Сумма бюджета.
     */
    public void setBudget(String category, double amount) {
        budgets.put(category, amount);
    }

    /**
     * Возвращает оставшийся бюджет для указанной категории.
     *
     * @param category Категория бюджета.
     * @return Оставшийся бюджет.
     */
    public double getRemainingBudget(String category) {
        return budgets.getOrDefault(category, 0.0) - expenses.getOrDefault(category, 0.0);
    }

    /**
     * Возвращает текущий баланс кошелька.
     *
     * @return Текущий баланс.
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Возвращает все доходы по категориям.
     *
     * @return Map, где ключ — категория, значение — сумма дохода.
     */
    public Map<String, Double> getIncomes() {
        return incomes;
    }

    /**
     * Возвращает все расходы по категориям.
     *
     * @return Map, где ключ — категория, значение — сумма расхода.
     */
    public Map<String, Double> getExpenses() {
        return expenses;
    }

    /**
     * Возвращает все бюджеты по категориям.
     *
     * @return Map, где ключ — категория, значение — сумма бюджета.
     */
    public Map<String, Double> getBudgets() {
        return budgets;
    }
}