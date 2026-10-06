package com.io.github.cawodevelopment.expense_tracker.user.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record UserChangePasswordRequest(
        @NotBlank
        @Length(min = 8)
        String password
) {
}
