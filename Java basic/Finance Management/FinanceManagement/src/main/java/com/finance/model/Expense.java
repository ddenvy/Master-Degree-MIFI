package com.finance.model;

/**
 * Класс Expense представляет расходы.
 * Он наследует абстрактный класс Transaction и реализует метод apply,
 * который добавляет расходы в кошелек пользователя.
 */
public class Expense extends Transaction {

    /**
     * Конструктор класса Expense.
     *
     * @param category Категория расхода (например, "Еда" или "Транспорт").
     * @param amount   Сумма расхода.
     */
    public Expense(String category, double amount) {
        super(category, amount); // Вызов конструктора родительского класса Transaction
    }

    /**
     * Применяет расход к кошельку пользователя.
     * Вызывает метод addExpense кошелька, чтобы добавить сумму расхода в указанную категорию.
     *
     * @param wallet Кошелек, к которому применяется расход.
     */
    @Override
    public void apply(Wallet wallet) {
        wallet.addExpense(getCategory(), getAmount()); // Добавляем расход в кошелек
    }
}