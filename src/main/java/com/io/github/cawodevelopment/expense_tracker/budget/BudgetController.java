package com.io.github.cawodevelopment.expense_tracker.budget;

import com.io.github.cawodevelopment.expense_tracker.budget.dto.BudgetRequest;
import com.io.github.cawodevelopment.expense_tracker.budget.dto.BudgetResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/api/v1/budgets")
public class BudgetController {
    @GetMapping
    public ResponseEntity<List<BudgetResponse>> getBudgets() {

    }

    @GetMapping("/{id}")
    public ResponseEntity<BudgetResponse> getBudgetById() {

    }

    @PostMapping
    public ResponseEntity<BudgetResponse> createBudget(@Valid @RequestBody BudgetRequest request) {

    }

    @PutMapping("/{id}")
    public ResponseEntity<BudgetResponse> updateBudget(@Valid @RequestBody BudgetRequest request) {

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BudgetResponse> deleteBudget() {}
}
