package com.io.github.cawodevelopment.expense_tracker.expense;

import com.io.github.cawodevelopment.expense_tracker.expense.dto.ExpenseRequest;
import com.io.github.cawodevelopment.expense_tracker.expense.dto.ExpenseResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController("/api/v1/expenses")
public class ExpenseController {

    @GetMapping
    public ResponseEntity<List<ExpenseResponse>> getExpenses() {

    }

    @GetMapping("/{id}")
    public ResponseEntity<ExpenseResponse> getExpenseById() {

    }

    @PostMapping
    public ResponseEntity<ExpenseResponse> createExpense(@Valid @RequestBody ExpenseRequest request) {

    }

    @PutMapping("/{id}")
    public ResponseEntity<ExpenseResponse> updateExpense(@Valid @RequestBody ExpenseRequest request) {

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ExpenseResponse> deleteExpense() {}
}
