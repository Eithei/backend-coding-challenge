package com.engagetech.utilities;

import com.engagetech.entities.expenses.Expense;

import java.math.BigDecimal;
import java.time.LocalDate;

// Class used only for generating test entities in order to make domain model changes easier.
public class TestEntityFactory {
    public static Expense getTestExpenseWithoutId() {
        return Expense.builder()
                .amount(new BigDecimal(10.00))
                .vat(new BigDecimal(2.00))
                .date(LocalDate.now())
                .reason("New Car")
                .build();
    }

    public static Expense getTestExpenseWithId() {
        return Expense.builder()
                .id(1L)
                .amount(new BigDecimal(10.00))
                .vat(new BigDecimal(2.00))
                .date(LocalDate.now())
                .reason("New Car")
                .build();
    }
}
