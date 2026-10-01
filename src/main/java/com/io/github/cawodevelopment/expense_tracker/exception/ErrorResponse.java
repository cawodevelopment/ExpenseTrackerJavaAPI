package com.io.github.cawodevelopment.expense_tracker.exception;

public record ErrorResponse(
        int status,
        String message
) {
}
