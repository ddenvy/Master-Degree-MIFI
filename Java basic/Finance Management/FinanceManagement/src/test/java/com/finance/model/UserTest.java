package com.finance.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Класс UserTest содержит unit-тесты для класса User.
 * Тесты проверяют корректность создания пользователя и получения его свойств.
 */
class UserTest {

    /**
     * Тест для проверки создания пользователя.
     * Проверяет, что:
     * 1. Логин пользователя корректно устанавливается и возвращается.
     * 2. Пароль пользователя корректно устанавливается и возвращается.
     * 3. Кошелек пользователя создается и не равен null.
     */
    @Test
    void testUserCreation() {
        // Создаем нового пользователя
        User user = new User("testUser", "password123");

        // Проверяем, что логин пользователя установлен корректно
        assertEquals("testUser", user.getUsername());

        // Проверяем, что пароль пользователя установлен корректно
        assertEquals("password123", user.getPassword());

        // Проверяем, что кошелек пользователя создан и не равен null
        assertNotNull(user.getWallet());
    }
}