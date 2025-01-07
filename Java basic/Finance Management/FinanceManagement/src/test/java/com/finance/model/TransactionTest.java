package com.finance.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Класс TransactionTest содержит unit-тесты для абстрактного класса Transaction.
 * Тесты проверяют корректность создания транзакций и получения их свойств.
 */
class TransactionTest {

    /**
     * Тест для проверки создания транзакции.
     * Проверяет, что:
     * 1. Категория транзакции корректно устанавливается и возвращается.
     * 2. Сумма транзакции корректно устанавливается и возвращается.
     */
    @Test
    void testTransactionCreation() {
        // Создаем объект дохода (Income), который является подклассом Transaction
        Transaction income = new Income("Salary", 1000.0);

        // Проверяем, что категория транзакции установлена корректно
        assertEquals("Salary", income.getCategory());

        // Проверяем, что сумма транзакции установлена корректно
        assertEquals(1000.0, income.getAmount());
    }
}