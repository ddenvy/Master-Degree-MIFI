package com.finance.manager;

import com.finance.model.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Класс FinanceManagerTest содержит unit-тесты для класса FinanceManager.
 * Тесты проверяют корректность регистрации, входа и выхода пользователей.
 */
class FinanceManagerTest {

    /**
     * Тест для проверки регистрации пользователя.
     * Проверяет, что:
     * 1. Новый пользователь успешно регистрируется.
     * 2. Попытка зарегистрировать пользователя с уже существующим логином возвращает false.
     */
    @Test
    void testRegisterUser() {
        FinanceManager manager = new FinanceManager();
        // Проверяем успешную регистрацию нового пользователя
        assertTrue(manager.registerUser("testUser", "password123"));
        // Проверяем, что повторная регистрация с тем же логином невозможна
        assertFalse(manager.registerUser("testUser", "password123")); // Пользователь уже существует
    }

    /**
     * Тест для проверки входа пользователя.
     * Проверяет, что:
     * 1. Пользователь может войти с правильными учетными данными.
     * 2. Пользователь не может войти с неправильным паролем.
     * 3. Пользователь не может войти с несуществующим логином.
     */
    @Test
    void testLogin() {
        FinanceManager manager = new FinanceManager();
        // Регистрируем пользователя
        manager.registerUser("testUser", "password123");
        // Проверяем успешный вход с правильными учетными данными
        assertTrue(manager.login("testUser", "password123"));
        // Проверяем, что вход с неправильным паролем невозможен
        assertFalse(manager.login("testUser", "wrongPassword")); // Неверный пароль
        // Проверяем, что вход с несуществующим логином невозможен
        assertFalse(manager.login("unknownUser", "password123")); // Пользователь не существует
    }

    /**
     * Тест для проверки выхода пользователя.
     * Проверяет, что:
     * 1. После выхода текущий пользователь становится null.
     */
    @Test
    void testLogout() {
        FinanceManager manager = new FinanceManager();
        // Регистрируем и входим под пользователем
        manager.registerUser("testUser", "password123");
        manager.login("testUser", "password123");
        // Выходим из системы
        manager.logout();
        // Проверяем, что текущий пользователь стал null
        assertNull(manager.getCurrentUser());
    }
}