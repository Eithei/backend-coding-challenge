package com.engagetech.repositories;

import com.engagetech.entities.expenses.Expense;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.util.List;

import static com.engagetech.utilities.TestEntityFactory.getTestExpenseWithoutId;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class ExpensesRepositoryTest {

    @Autowired
    private ExpensesRepository expensesRepository;

    private Expense expense;

    @Before
    public void setup() {
        expense = getTestExpenseWithoutId();
    }

    @Test
    public void testSave_WhenValidExpenseProvided_ShouldReturnValidId() {
        final Expense savedExpense = expensesRepository.save(expense);
        assertNotNull(savedExpense.getId());
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void testSave_WhenNullIsProvided_ShouldThrowAnError() {
        expensesRepository.save((Expense) null);
    }

    @Test
    public void testFindAll_WhenExpensesFound_ShouldReturnCollection() {
        expensesRepository.save(expense);
        final List<Expense> expenses = expensesRepository.findAll();
        assertEquals(1, expenses.size());
    }

    @Test
    public void testFindAll_WhenNoExpenseFound_ShouldReturnEmptyCollection() {
        final List<Expense> expenses = expensesRepository.findAll();
        assertTrue(expenses.isEmpty());
    }
}
