package com.engagetech.services.expenses;

import com.engagetech.entities.expenses.Expense;
import com.engagetech.repositories.ExpensesRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collections;
import java.util.List;

import static com.engagetech.utilities.TestEntityFactory.getTestExpenseWithoutId;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class ExpensesServiceTest {
    @Mock
    private ExpensesRepository expensesRepository;

    private ExpensesService expensesService;

    @Before
    public void setup() {
        expensesService = new ExpensesService(expensesRepository);
        final Expense expense = getTestExpenseWithoutId();

        when(expensesRepository.save(expense)).thenReturn(expense);
        when(expensesRepository.save((Expense) null)).thenThrow(new InvalidDataAccessApiUsageException("Invalid parameter"));
        when(expensesRepository.findAll()).thenReturn(Collections.singletonList(expense));
    }

    @Test
    public void testSave_WhenValidExpenseProvided_ShouldReturnValidId() {
        final Expense expense = getTestExpenseWithoutId();
        final Expense savedExpense = expensesService.save(expense);
        assertNotNull(savedExpense.getAmount());
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void testSave_WhenNullIsProvided_ShouldThrowAnError() {
        expensesService.save(null);
    }

    @Test
    public void testFindAll_WhenExpensesFound_ShouldReturnCollection() {
        final List<Expense> expenses = expensesService.findAll();
        assertEquals(1, expenses.size());
    }

    @Test
    public void testFindAll_WhenNoExpenseFound_ShouldReturnEmptyCollection() {
        when(expensesRepository.findAll()).thenReturn(Collections.emptyList());
        final List<Expense> expenses = expensesService.findAll();
        assertTrue(expenses.isEmpty());
    }
}
