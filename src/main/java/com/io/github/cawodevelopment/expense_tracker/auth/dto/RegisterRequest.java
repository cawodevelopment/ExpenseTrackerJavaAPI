package com.io.github.cawodevelopment.expense_tracker.auth.dto;

import jakarta.validation.constraints.NotBlank;

public record RegisterRequest(
        @NotBlank
        String email,

        @NotBlank
        String password
) { }
