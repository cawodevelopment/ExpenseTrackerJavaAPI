package com.io.github.cawodevelopment.expense_tracker.dashboard.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record SpendingOverTimeResponse(
        LocalDate date,
        BigDecimal total
) {}
