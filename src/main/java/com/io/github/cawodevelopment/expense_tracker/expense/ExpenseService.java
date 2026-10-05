package com.io.github.cawodevelopment.expense_tracker.expense;

import com.io.github.cawodevelopment.expense_tracker.exception.ResourceNotFoundException;
import com.io.github.cawodevelopment.expense_tracker.expense.dto.ExpenseRequest;
import com.io.github.cawodevelopment.expense_tracker.expense.dto.ExpenseResponse;
import com.io.github.cawodevelopment.expense_tracker.user.User;
import com.io.github.cawodevelopment.expense_tracker.user.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final ExpenseMapper expenseMapper;
    private final UserRepository userRepository;

    public ExpenseService(ExpenseRepository expenseRepository, ExpenseMapper expenseMapper, UserRepository userRepository) {
        this.expenseRepository = expenseRepository;
        this.expenseMapper = expenseMapper;
        this.userRepository = userRepository;
    }

    public List<ExpenseResponse> getExpenses(Authentication authentication) {
        List<Expense> expenses = expenseRepository.findAllByUsername(authentication.getName());

        return expenses
                .stream()
                .map(expense -> expenseMapper.toExpenseResponse(expense))
                .toList();
    }

    public ExpenseResponse getExpenseById(Authentication authentication, Long id) {
        Expense expense = expenseRepository
                .findByUsernameAndId(authentication.getName(), id)
                .orElseThrow(() -> new ResourceNotFoundException("Expense", id));

        return expenseMapper.toExpenseResponse(expense);
    }

    public ExpenseResponse createExpense(Authentication authentication, ExpenseRequest request){
        Expense expense = expenseMapper.toExpenseEntity(request);
        expense.setCreatedAt(LocalDate.now());

        User user = userRepository
                .findByUsername(authentication.getName())
                .orElseThrow(() -> new IllegalStateException("Username not found"));

        expense.setUser(user);

        expenseRepository.save(expense);
        return expenseMapper.toExpenseResponse(expense);
    }

    public ExpenseResponse updateExpenseById(Authentication authentication, Long id, ExpenseRequest request){
        Expense expense = expenseRepository
                .findByUsernameAndId(authentication.getName(), id)
                .orElseThrow(() -> new ResourceNotFoundException("Expense", id));

        expense.setDescription(request.description());
        expense.setCategory(request.category());
        expense.setAmount(request.amount());
        expense.setDate(request.date());

        expenseRepository.save(expense);
        return expenseMapper.toExpenseResponse(expense);
    }

    public void deleteExpenseById(Authentication authentication, Long id){
        Expense expense = expenseRepository
                .findByUsernameAndId(authentication.getName(), id)
                .orElseThrow(() -> new ResourceNotFoundException("Expense", id));

        expenseRepository.delete(expense);
    }
}
