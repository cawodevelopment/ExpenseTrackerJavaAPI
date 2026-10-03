package com.io.github.cawodevelopment.expense_tracker.budget;

import com.io.github.cawodevelopment.expense_tracker.budget.dto.BudgetRequest;
import com.io.github.cawodevelopment.expense_tracker.budget.dto.BudgetResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/v1/budgets")
public class BudgetController {

    private BudgetService budgetService;

    public BudgetController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    @GetMapping
    public ResponseEntity<List<BudgetResponse>> getBudgets() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(budgetService.getBudgets());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BudgetResponse> getBudgetById(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(budgetService.getBudgetById(id));
    }

    @PostMapping
    public ResponseEntity<BudgetResponse> createBudget(@Valid @RequestBody BudgetRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(budgetService.createBudget(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BudgetResponse> updateBudgetById(@PathVariable Long id, @Valid @RequestBody BudgetRequest request) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(budgetService.updateBudgetById(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBudgetById(@PathVariable Long id) {
        budgetService.deleteBudgetById(id);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
