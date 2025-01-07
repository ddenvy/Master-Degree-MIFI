package com.finance.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TransactionTest {

    @Test
    void testTransactionCreation() {
        Transaction income = new Income("Salary", 1000.0);
        assertEquals("Salary", income.getCategory());
        assertEquals(1000.0, income.getAmount());
    }
}