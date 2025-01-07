package com.finance.model;

/**
 * Абстрактный класс Transaction представляет финансовую операцию.
 * Он служит базовым классом для доходов (Income) и расходов (Expense).
 * Класс содержит общие поля и методы для всех типов транзакций.
 */
public abstract class Transaction {
    private String category; // Категория транзакции (например, "Зарплата" или "Еда")
    private double amount;   // Сумма транзакции

    /**
     * Конструктор класса Transaction.
     *
     * @param category Категория транзакции.
     * @param amount   Сумма транзакции.
     */
    public Transaction(String category, double amount) {
        this.category = category;
        this.amount = amount;
    }

    /**
     * Возвращает категорию транзакции.
     *
     * @return Категория транзакции.
     */
    public String getCategory() {
        return category;
    }

    /**
     * Возвращает сумму транзакции.
     *
     * @return Сумма транзакции.
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Абстрактный метод для применения транзакции к кошельку.
     * Должен быть реализован в дочерних классах (Income и Expense).
     *
     * @param wallet Кошелек, к которому применяется транзакция.
     */
    public abstract void apply(Wallet wallet);
}