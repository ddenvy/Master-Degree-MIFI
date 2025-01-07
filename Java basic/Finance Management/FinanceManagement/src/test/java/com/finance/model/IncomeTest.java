package com.finance.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Класс IncomeTest содержит unit-тесты для класса Income.
 * Тесты проверяют корректность применения доходов к кошельку.
 */
class IncomeTest {

    /**
     * Тест для проверки применения дохода к кошельку.
     * Проверяет, что:
     * 1. После применения дохода баланс кошелька увеличивается на сумму дохода.
     * 2. Доход добавляется в список доходов кошелька.
     */
    @Test
    void testApplyIncome() {
        // Создаем кошелек
        Wallet wallet = new Wallet();

        // Создаем доход на категорию "Salary" с суммой 1000.0
        Income income = new Income("Salary", 1000.0);

        // Применяем доход к кошельку
        income.apply(wallet);

        // Проверяем, что баланс кошелька увеличился на сумму дохода
        assertEquals(1000.0, wallet.getBalance());

        // Проверяем, что доход добавлен в список доходов кошелька
        assertEquals(1000.0, wallet.getIncomes().get("Salary"));
    }
}