package com.finance.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Класс ExpenseTest содержит unit-тесты для класса Expense.
 * Тесты проверяют корректность применения расходов к кошельку.
 */
class ExpenseTest {

    /**
     * Тест для проверки применения расхода к кошельку.
     * Проверяет, что:
     * 1. После применения расхода баланс кошелька уменьшается на сумму расхода.
     * 2. Расход добавляется в список расходов кошелька.
     */
    @Test
    void testApplyExpense() {
        // Создаем кошелек и добавляем начальный доход
        Wallet wallet = new Wallet();
        wallet.addIncome("Salary", 1000.0);

        // Создаем расход на категорию "Food" с суммой 200.0
        Expense expense = new Expense("Food", 200.0);

        // Применяем расход к кошельку
        expense.apply(wallet);

        // Проверяем, что баланс кошелька уменьшился на сумму расхода
        assertEquals(800.0, wallet.getBalance());

        // Проверяем, что расход добавлен в список расходов кошелька
        assertEquals(200.0, wallet.getExpenses().get("Food"));
    }
}