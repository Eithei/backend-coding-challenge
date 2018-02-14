package com.engagetech.services.expenses;

import com.engagetech.entities.expenses.Expense;
import com.engagetech.repositories.ExpensesRepository;
import com.engagetech.services.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ExpensesService implements CrudService<Expense> {
    private ExpensesRepository expensesRepository;

    @Autowired
    public ExpensesService(ExpensesRepository expensesRepository) {
        this.expensesRepository = expensesRepository;
    }

    @Override
    public Expense save(Expense expense) {
        return expensesRepository.save(expense);
    }

    @Override
    public List<Expense> findAll() {
        return expensesRepository.findAll();
    }
}
