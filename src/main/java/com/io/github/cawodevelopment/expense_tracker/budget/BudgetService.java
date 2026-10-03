package com.io.github.cawodevelopment.expense_tracker.budget;

import com.io.github.cawodevelopment.expense_tracker.budget.dto.BudgetRequest;
import com.io.github.cawodevelopment.expense_tracker.budget.dto.BudgetResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class BudgetService {

    private BudgetRepository budgetRepository;
    private BudgetMapper budgetMapper;

    public BudgetService(BudgetRepository budgetRepository, BudgetMapper budgetMapper) {
        this.budgetRepository = budgetRepository;
        this.budgetMapper = budgetMapper;
    }

    public BudgetResponse getBudgetById(Long id){
        Budget budget = budgetRepository
                .findById(id)
                .orElseThrow();

        return budgetMapper.toBudgetResponse(budget);
    }

    public List<BudgetResponse> getBudgets() {
        List<Budget> budgets = budgetRepository.findAll();

        return budgets
                .stream()
                .map(budget -> budgetMapper.toBudgetResponse(budget))
                .toList();
    }

    public BudgetResponse createBudget(BudgetRequest request){
        Budget budget = budgetMapper.toBudgetEntity(request);
        budget.setCreatedAt(LocalDate.now());

        budgetRepository.save(budget);
        return budgetMapper.toBudgetResponse(budget);
    }

    public BudgetResponse updateBudgetById(Long id, BudgetRequest request){
        Budget budget = budgetRepository
                .findById(id)
                .orElseThrow();

        budget.setDescription(request.description());
        budget.setCategory(request.category());
        budget.setAmount(request.amount());
        budget.setStartDate(request.startDate());
        budget.setEndDate(request.endDate());

        budgetRepository.save(budget);
        return budgetMapper.toBudgetResponse(budget);
    }

    public void deleteBudgetById(Long id){
        budgetRepository
                .findById(id)
                .orElseThrow();

        budgetRepository.deleteById(id);
    }
}
