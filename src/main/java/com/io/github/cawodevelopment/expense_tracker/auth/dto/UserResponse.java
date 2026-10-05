package com.io.github.cawodevelopment.expense_tracker.auth.dto;

import java.time.LocalDate;

public record UserResponse(
        String email,
        LocalDate createdAt
) {
}
