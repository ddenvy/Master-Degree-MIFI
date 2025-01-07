package com.finance.manager;

import com.finance.model.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FinanceManagerTest {

    @Test
    void testRegisterUser() {
        FinanceManager manager = new FinanceManager();
        assertTrue(manager.registerUser("testUser", "password123"));
        assertFalse(manager.registerUser("testUser", "password123")); // Пользователь уже существует
    }

    @Test
    void testLogin() {
        FinanceManager manager = new FinanceManager();
        manager.registerUser("testUser", "password123");
        assertTrue(manager.login("testUser", "password123"));
        assertFalse(manager.login("testUser", "wrongPassword")); // Неверный пароль
        assertFalse(manager.login("unknownUser", "password123")); // Пользователь не существует
    }

    @Test
    void testLogout() {
        FinanceManager manager = new FinanceManager();
        manager.registerUser("testUser", "password123");
        manager.login("testUser", "password123");
        manager.logout();
        assertNull(manager.getCurrentUser());
    }
}