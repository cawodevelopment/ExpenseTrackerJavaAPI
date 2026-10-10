package com.io.github.cawodevelopment.expense_tracker.expense;

import com.io.github.cawodevelopment.expense_tracker.category.Category;
import com.io.github.cawodevelopment.expense_tracker.exception.ResourceNotFoundException;
import com.io.github.cawodevelopment.expense_tracker.expense.dto.ExpenseRequest;
import com.io.github.cawodevelopment.expense_tracker.expense.dto.ExpenseResponse;
import com.io.github.cawodevelopment.expense_tracker.expense.dto.ExpenseUpdateRequest;
import com.io.github.cawodevelopment.expense_tracker.user.User;
import com.io.github.cawodevelopment.expense_tracker.user.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

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

    public Page<ExpenseResponse> getExpenses(Authentication authentication,
                                             Pageable pageable,
                                             Category category,
                                             BigDecimal minAmount,
                                             BigDecimal maxAmount,
                                             LocalDate date) {
        Page<Expense> expenses = expenseRepository.findAllByEmail(
                authentication.getName(),
                pageable,
                category,
                minAmount,
                maxAmount,
                date
        );

        return expenses
                .map(expense -> expenseMapper.toExpenseResponse(expense));
    }

    public ExpenseResponse getExpenseById(Authentication authentication, Long id) {
        Expense expense = expenseRepository
            .findByUser_EmailAndId(authentication.getName(), id)
                .orElseThrow(() -> new ResourceNotFoundException("Expense", id));

        return expenseMapper.toExpenseResponse(expense);
    }

    public ExpenseResponse createExpense(Authentication authentication, ExpenseRequest request){
        Expense expense = expenseMapper.toExpenseEntity(request);
        expense.setCreatedAt(LocalDate.now());

        User user = userRepository
                .findByEmail(authentication.getName())
                .orElseThrow(() -> new IllegalStateException("Username not found"));

        expense.setUser(user);

        expenseRepository.save(expense);
        return expenseMapper.toExpenseResponse(expense);
    }

    public ExpenseResponse updateExpenseById(Authentication authentication, Long id, ExpenseUpdateRequest request){
        Expense expense = expenseRepository
            .findByUser_EmailAndId(authentication.getName(), id)
                .orElseThrow(() -> new ResourceNotFoundException("Expense", id));

        expenseMapper.updateExpenseFromRequest(expense, request);

        expenseRepository.save(expense);
        return expenseMapper.toExpenseResponse(expense);
    }

    public void deleteExpenseById(Authentication authentication, Long id){
        Expense expense = expenseRepository
            .findByUser_EmailAndId(authentication.getName(), id)
                .orElseThrow(() -> new ResourceNotFoundException("Expense", id));

        expenseRepository.delete(expense);
    }
}
