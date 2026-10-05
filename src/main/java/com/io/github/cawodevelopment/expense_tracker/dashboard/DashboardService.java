package com.io.github.cawodevelopment.expense_tracker.dashboard;

import com.io.github.cawodevelopment.expense_tracker.budget.BudgetRepository;
import com.io.github.cawodevelopment.expense_tracker.dashboard.dto.SpendingOverTimeResponse;
import com.io.github.cawodevelopment.expense_tracker.expense.Expense;
import com.io.github.cawodevelopment.expense_tracker.expense.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class DashboardService {

    private final ExpenseRepository expenseRepository;

    public DashboardService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public List<SpendingOverTimeResponse> getSpendingOverTime() {

        Map<LocalDate, BigDecimal> spendingByDate = new TreeMap<>();

        List<Expense> expenses = expenseRepository.findAll();

        for (Expense expense : expenses) {
            LocalDate date = expense.getDate();
            BigDecimal amount = expense.getAmount();

            if (spendingByDate.containsKey(date)) {
                BigDecimal currentTotal = spendingByDate.get(date);
                spendingByDate.put(date, currentTotal.add(amount));
            } else {
                spendingByDate.put(date, amount);
            }
        }

        List<SpendingOverTimeResponse> responses = new ArrayList<>();

        for (Map.Entry<LocalDate, BigDecimal> entry : spendingByDate.entrySet()) {
            SpendingOverTimeResponse response = new SpendingOverTimeResponse(
                    entry.getKey(),
                    entry.getValue()
            );

            responses.add(response);
        }

        return responses;
    }

    public BigDecimal getSpendingByCategory(String category) {
        BigDecimal total = BigDecimal.ZERO;

        List<Expense> expenses = expenseRepository.findAll();

        for (Expense expense : expenses) {
            if (expense.getCategory().name().equalsIgnoreCase(category)) {
                total = total.add(expense.getAmount());
            }
        }

        return total;
    }
}
