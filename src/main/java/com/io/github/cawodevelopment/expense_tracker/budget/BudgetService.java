package com.io.github.cawodevelopment.expense_tracker.budget;

import com.io.github.cawodevelopment.expense_tracker.budget.dto.BudgetRequest;
import com.io.github.cawodevelopment.expense_tracker.budget.dto.BudgetResponse;
import com.io.github.cawodevelopment.expense_tracker.exception.ResourceNotFoundException;
import com.io.github.cawodevelopment.expense_tracker.user.User;
import com.io.github.cawodevelopment.expense_tracker.user.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

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
                .findByUsernameAndId(authentication.getName(), id)
                .orElseThrow(() -> new ResourceNotFoundException("Expense", id));

        return budgetMapper.toBudgetResponse(budget);
    }

    public List<BudgetResponse> getBudgets(Authentication authentication) {
        List<Budget> budgets = budgetRepository.findAllByUsername(authentication.getName());

        return budgets
                .stream()
                .map(budget -> budgetMapper.toBudgetResponse(budget))
                .toList();
    }

    public BudgetResponse createBudget(Authentication authentication, BudgetRequest request){
        Budget budget = budgetMapper.toBudgetEntity(request);
        budget.setCreatedAt(LocalDate.now());

        User user = userRepository
                .findByUsername(authentication.getName())
                .orElseThrow(() -> new IllegalStateException("Username not found"));

        budget.setUser(user);

        budgetRepository.save(budget);
        return budgetMapper.toBudgetResponse(budget);
    }

    public BudgetResponse updateBudgetById(Authentication authentication, Long id, BudgetRequest request){
        Budget budget = budgetRepository
                .findByUsernameAndId(authentication.getName(), id)
                .orElseThrow(() -> new ResourceNotFoundException("Expense", id));

        budget.setDescription(request.description());
        budget.setCategory(request.category());
        budget.setAmount(request.amount());
        budget.setStartDate(request.startDate());
        budget.setEndDate(request.endDate());

        budgetRepository.save(budget);
        return budgetMapper.toBudgetResponse(budget);
    }

    public void deleteBudgetById(Authentication authentication, Long id){
        budgetRepository
                .findByUsernameAndId(authentication.getName(), id)
                .orElseThrow(() -> new ResourceNotFoundException("Expense", id));

        budgetRepository.deleteById(id);
    }
}
