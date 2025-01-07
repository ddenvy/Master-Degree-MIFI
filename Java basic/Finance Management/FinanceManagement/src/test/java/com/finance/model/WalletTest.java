package com.finance.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Класс WalletTest содержит unit-тесты для класса Wallet.
 * Тесты проверяют корректность работы методов, связанных с доходами, расходами, бюджетами и балансом.
 */
class WalletTest {

    /**
     * Тест для проверки добавления дохода.
     * Проверяет, что:
     * 1. Баланс кошелька увеличивается на сумму дохода.
     * 2. Доход добавляется в список доходов кошелька.
     */
    @Test
    void testAddIncome() {
        // Создаем кошелек
        Wallet wallet = new Wallet();

        // Добавляем доход на категорию "Salary" с суммой 1000.0
        wallet.addIncome("Salary", 1000.0);

        // Проверяем, что баланс кошелька увеличился на сумму дохода
        assertEquals(1000.0, wallet.getBalance());

        // Проверяем, что доход добавлен в список доходов кошелька
        assertEquals(1000.0, wallet.getIncomes().get("Salary"));
    }

    /**
     * Тест для проверки добавления расхода.
     * Проверяет, что:
     * 1. Баланс кошелька уменьшается на сумму расхода.
     * 2. Расход добавляется в список расходов кошелька.
     */
    @Test
    void testAddExpense() {
        // Создаем кошелек и добавляем начальный доход
        Wallet wallet = new Wallet();
        wallet.addIncome("Salary", 1000.0);

        // Добавляем расход на категорию "Food" с суммой 200.0
        wallet.addExpense("Food", 200.0);

        // Проверяем, что баланс кошелька уменьшился на сумму расхода
        assertEquals(800.0, wallet.getBalance());

        // Проверяем, что расход добавлен в список расходов кошелька
        assertEquals(200.0, wallet.getExpenses().get("Food"));
    }

    /**
     * Тест для проверки установки бюджета.
     * Проверяет, что:
     * 1. Бюджет для указанной категории корректно устанавливается.
     */
    @Test
    void testSetBudget() {
        // Создаем кошелек
        Wallet wallet = new Wallet();

        // Устанавливаем бюджет на категорию "Food" с суммой 500.0
        wallet.setBudget("Food", 500.0);

        // Проверяем, что бюджет установлен корректно
        assertEquals(500.0, wallet.getBudgets().get("Food"));
    }

    /**
     * Тест для проверки получения оставшегося бюджета.
     * Проверяет, что:
     * 1. Оставшийся бюджет для указанной категории корректно рассчитывается.
     */
    @Test
    void testGetRemainingBudget() {
        // Создаем кошелек
        Wallet wallet = new Wallet();

        // Устанавливаем бюджет на категорию "Food" с суммой 500.0
        wallet.setBudget("Food", 500.0);

        // Добавляем расход на категорию "Food" с суммой 200.0
        wallet.addExpense("Food", 200.0);

        // Проверяем, что оставшийся бюджет рассчитан корректно
        assertEquals(300.0, wallet.getRemainingBudget("Food"));
    }
}