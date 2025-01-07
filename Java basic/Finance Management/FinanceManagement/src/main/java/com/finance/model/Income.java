package com.finance.model;

/**
 * Класс Income представляет доходы.
 * Он наследует абстрактный класс Transaction и реализует метод apply,
 * который добавляет доходы в кошелек пользователя.
 */
public class Income extends Transaction {

    /**
     * Конструктор класса Income.
     *
     * @param category Категория дохода (например, "Зарплата" или "Бонус").
     * @param amount   Сумма дохода.
     */
    public Income(String category, double amount) {
        super(category, amount); // Вызов конструктора родительского класса Transaction
    }

    /**
     * Применяет доход к кошельку пользователя.
     * Вызывает метод addIncome кошелька, чтобы добавить сумму дохода в указанную категорию.
     *
     * @param wallet Кошелек, к которому применяется доход.
     */
    @Override
    public void apply(Wallet wallet) {
        wallet.addIncome(getCategory(), getAmount()); // Добавляем доход в кошелек
    }
}