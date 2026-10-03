package com.io.github.cawodevelopment.expense_tracker.expense;

import com.io.github.cawodevelopment.expense_tracker.exception.ResourceNotFoundException;
import com.io.github.cawodevelopment.expense_tracker.expense.dto.ExpenseRequest;
import com.io.github.cawodevelopment.expense_tracker.expense.dto.ExpenseResponse;
import jakarta.persistence.NoResultException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ExpenseService {

    private ExpenseRepository expenseRepository;
    private ExpenseMapper expenseMapper;

    public ExpenseService(ExpenseRepository expenseRepository, ExpenseMapper expenseMapper) {
        this.expenseRepository = expenseRepository;
        this.expenseMapper = expenseMapper;
    }

    public ExpenseResponse getExpenseById(Long id) {
        Expense expense = expenseRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Expense", id));

        return expenseMapper.toExpenseResponse(expense);
    }

    public List<ExpenseResponse> getExpenses() {
        List<Expense> expenses = expenseRepository.findAll();

        return expenses
                .stream()
                .map(expense -> expenseMapper.toExpenseResponse(expense))
                .toList();
    }

    public ExpenseResponse createExpense(ExpenseRequest request){
        Expense expense = expenseMapper.toExpenseEntity(request);
        expense.setCreatedAt(LocalDate.now());

        expenseRepository.save(expense);
        return expenseMapper.toExpenseResponse(expense);
    }

    public ExpenseResponse updateExpenseById(Long id, ExpenseRequest request){
        Expense expense = expenseRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Expense", id));

        expense.setDescription(request.description());
        expense.setCategory(request.category());
        expense.setAmount(request.amount());
        expense.setDate(request.date());

        expenseRepository.save(expense);
        return expenseMapper.toExpenseResponse(expense);
    }

    public void deleteExpenseById(Long id){
        Expense expense = expenseRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Expense", id));

        expenseRepository.delete(expense);
    }
}
