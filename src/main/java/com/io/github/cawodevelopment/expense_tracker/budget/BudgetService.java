package com.io.github.cawodevelopment.expense_tracker.budget;

import com.io.github.cawodevelopment.expense_tracker.budget.dto.BudgetRequest;
import com.io.github.cawodevelopment.expense_tracker.budget.dto.BudgetResponse;
import com.io.github.cawodevelopment.expense_tracker.budget.dto.BudgetUpdateRequest;
import com.io.github.cawodevelopment.expense_tracker.category.Category;
import com.io.github.cawodevelopment.expense_tracker.exception.ResourceNotFoundException;
import com.io.github.cawodevelopment.expense_tracker.user.User;
import com.io.github.cawodevelopment.expense_tracker.user.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class BudgetService {

    private final BudgetRepository budgetRepository;
    private final BudgetMapper budgetMapper;
    private final UserRepository userRepository;

    public BudgetService(BudgetRepository budgetRepository, BudgetMapper budgetMapper, UserRepository userRepository) {
        this.budgetRepository = budgetRepository;
        this.budgetMapper = budgetMapper;
        this.userRepository = userRepository;
    }

    public BudgetResponse getBudgetById(Authentication authentication, Long id){
        Budget budget = budgetRepository
            .findByUser_EmailAndId(authentication.getName(), id)
                .orElseThrow(() -> new ResourceNotFoundException("Expense", id));

        return budgetMapper.toBudgetResponse(budget);
    }

    public Page<BudgetResponse> getBudgets(Authentication authentication,
                                           Pageable pageable,
                                           Category category,
                                           BigDecimal amount,
                                           LocalDate startDate,
                                           LocalDate endDate
                                           ) {
        Page<Budget> budgets = budgetRepository.findAllByEmail(
                authentication.getName(),
                pageable,
                category,
                amount,
                startDate,
                endDate);

        return budgets
                .map(budget -> budgetMapper.toBudgetResponse(budget));
    }

    public BudgetResponse createBudget(Authentication authentication, BudgetRequest request){
        Budget budget = budgetMapper.toBudgetEntity(request);
        budget.setCreatedAt(LocalDate.now());

        User user = userRepository
                .findByEmail(authentication.getName())
                .orElseThrow(() -> new IllegalStateException("Username not found"));

        budget.setUser(user);

        budgetRepository.save(budget);
        return budgetMapper.toBudgetResponse(budget);
    }

    public BudgetResponse updateBudgetById(Authentication authentication, Long id, BudgetUpdateRequest request){
        Budget budget = budgetRepository
            .findByUser_EmailAndId(authentication.getName(), id)
                .orElseThrow(() -> new ResourceNotFoundException("Expense", id));

        budgetMapper.updateBudgetFromRequest(budget, request);

        budgetRepository.save(budget);
        return budgetMapper.toBudgetResponse(budget);
    }

    public void deleteBudgetById(Authentication authentication, Long id){
        budgetRepository
            .findByUser_EmailAndId(authentication.getName(), id)
                .orElseThrow(() -> new ResourceNotFoundException("Expense", id));

        budgetRepository.deleteById(id);
    }
}
