package com.engagetech.controllers.expenses;

import com.engagetech.entities.expenses.Expense;
import com.engagetech.services.expenses.ExpensesService;
import com.engagetech.utilities.TestRestRequestHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collections;
import java.util.List;

import static com.engagetech.utilities.TestEntityFactory.getTestExpenseWithId;
import static com.engagetech.utilities.TestEntityFactory.getTestExpenseWithoutId;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ExpensesControllerTest {
    @MockBean
    private ExpensesService expensesService;
    @LocalServerPort
    private int port;

    private TestRestRequestHelper restRequestHelper;

    private String expensesUrl;

    @Before
    public void setup() {
        restRequestHelper = new TestRestRequestHelper();
        expensesUrl = "http://localhost:" + port + "/app/expenses";
        final Expense expense = getTestExpenseWithId();

        when(expensesService.save(any(Expense.class))).thenReturn(expense);
        when(expensesService.findAll()).thenReturn(Collections.singletonList(expense));
    }

    @Test
    public void testSaveExpense_WhenExpenseIsPassed_ShouldReturnOneWithId() {
        final Expense expense = getTestExpenseWithoutId();

        final ResponseEntity response = restRequestHelper.sendPostRequest(expensesUrl, expense, Expense.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());

        final Expense savedExpense = (Expense) response.getBody();
        assertNotNull(savedExpense.getId());
    }

    @Test
    public void testSaveExpense_WhenNullIsPassed_ShouldReturnBadRequest() {
        final ResponseEntity<Expense> response = restRequestHelper.sendPostRequest(expensesUrl, null, Expense.class);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testGetExpenses_WhenNoneArePersisted_ShouldReturnEmptyCollection() {
        when(expensesService.findAll()).thenReturn(Collections.emptyList());

        final ResponseEntity<List> response = restRequestHelper.sendGetRequest(expensesUrl, List.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());

        final List<Expense> expenses = (List<Expense>) response.getBody();
        assertTrue(expenses.isEmpty());
    }

    @Test
    public void testGetExpenses_WhenExpensesArePersisted_ShouldReturnCollection() {
        final Expense expense = getTestExpenseWithoutId();
        restRequestHelper.sendPostRequest(expensesUrl, expense, Expense.class);

        final ResponseEntity<List> response = restRequestHelper.sendGetRequest(expensesUrl, List.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());

        final List<Expense> expenses = (List<Expense>) response.getBody();
        assertEquals(1, expenses.size());
    }
}
