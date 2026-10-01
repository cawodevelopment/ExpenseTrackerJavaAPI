package com.io.github.cawodevelopment.expense_tracker.expense.dto;

import com.io.github.cawodevelopment.expense_tracker.category.Category;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ExpenseResponse(
        Long id,
        String description,
        Category category,
        BigDecimal amount,
        LocalDate date,
        LocalDate createdAt
) {
}
