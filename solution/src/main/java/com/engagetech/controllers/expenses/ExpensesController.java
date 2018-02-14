package com.engagetech.controllers.expenses;

import com.engagetech.entities.expenses.Expense;
import com.engagetech.services.CrudService;
import com.engagetech.services.expenses.ExpensesService;
import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiPathParam;
import org.jsondoc.core.pojo.ApiVisibility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(name = "Expenses Controller", description = "Handling details about expenses", visibility = ApiVisibility.PUBLIC)
@RestController
@RequestMapping(value = "app/expenses")
public class ExpensesController {

    private CrudService<Expense> expenseCrudService;

    @Autowired
    public ExpensesController(ExpensesService expensesService) {
        this.expenseCrudService = expensesService;
    }

    @ApiMethod(description = "Save the expense details.", visibility = ApiVisibility.PUBLIC)
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Expense> saveExpense(@ApiPathParam(description = "Expense submitted for saving.")
                                               @RequestBody Expense expense) {
        final Expense savedExpense = expenseCrudService.save(expense);
        return new ResponseEntity<>(savedExpense, HttpStatus.CREATED);
    }

    @ApiMethod(description = "Retrieves submitted expenses.", visibility = ApiVisibility.PUBLIC)
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<Expense>> getExpenses() {
        final List<Expense> expenses = expenseCrudService.findAll();
        return new ResponseEntity<>(expenses, HttpStatus.OK);
    }
}
