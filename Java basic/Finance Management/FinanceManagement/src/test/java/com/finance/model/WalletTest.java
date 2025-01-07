package com.finance.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class WalletTest {

    @Test
    void testAddIncome() {
        Wallet wallet = new Wallet();
        wallet.addIncome("Salary", 1000.0);
        assertEquals(1000.0, wallet.getBalance());
        assertEquals(1000.0, wallet.getIncomes().get("Salary"));
    }

    @Test
    void testAddExpense() {
        Wallet wallet = new Wallet();
        wallet.addIncome("Salary", 1000.0);
        wallet.addExpense("Food", 200.0);
        assertEquals(800.0, wallet.getBalance());
        assertEquals(200.0, wallet.getExpenses().get("Food"));
    }

    @Test
    void testSetBudget() {
        Wallet wallet = new Wallet();
        wallet.setBudget("Food", 500.0);
        assertEquals(500.0, wallet.getBudgets().get("Food"));
    }

    @Test
    void testGetRemainingBudget() {
        Wallet wallet = new Wallet();
        wallet.setBudget("Food", 500.0);
        wallet.addExpense("Food", 200.0);
        assertEquals(300.0, wallet.getRemainingBudget("Food"));
    }
}