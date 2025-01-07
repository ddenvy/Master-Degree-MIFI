package com.finance.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class IncomeTest {

    @Test
    void testApplyIncome() {
        Wallet wallet = new Wallet();
        Income income = new Income("Salary", 1000.0);
        income.apply(wallet);
        assertEquals(1000.0, wallet.getBalance());
        assertEquals(1000.0, wallet.getIncomes().get("Salary"));
    }
}