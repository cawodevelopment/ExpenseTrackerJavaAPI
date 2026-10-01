package com.io.github.cawodevelopment.expense_tracker.budget.dto;

import com.io.github.cawodevelopment.expense_tracker.category.Category;
import java.math.BigDecimal;
import java.time.LocalDate;

public record BudgetResponse(
        Long id,
        String description,
        Category category,
        BigDecimal amount,
        LocalDate startDate,
        LocalDate endDate,
        LocalDate createdAt
) {
}
