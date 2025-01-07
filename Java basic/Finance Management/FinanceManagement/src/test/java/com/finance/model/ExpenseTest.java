package com.finance.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ExpenseTest {

    @Test
    void testApplyExpense() {
        Wallet wallet = new Wallet();
        wallet.addIncome("Salary", 1000.0);
        Expense expense = new Expense("Food", 200.0);
        expense.apply(wallet);
        assertEquals(800.0, wallet.getBalance());
        assertEquals(200.0, wallet.getExpenses().get("Food"));
    }
}