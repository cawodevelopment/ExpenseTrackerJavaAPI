package com.io.github.cawodevelopment.expense_tracker.budget;

import com.io.github.cawodevelopment.expense_tracker.budget.dto.BudgetRequest;
import com.io.github.cawodevelopment.expense_tracker.budget.dto.BudgetResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/budgets")
public class BudgetController {

    private final BudgetService budgetService;

    public BudgetController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    @GetMapping
    public ResponseEntity<Page<BudgetResponse>> getBudgets(Authentication authentication, Pageable pageable) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(budgetService.getBudgets(authentication, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BudgetResponse> getBudgetById(Authentication authentication, @PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(budgetService.getBudgetById(authentication, id));
    }

    @PostMapping
    public ResponseEntity<BudgetResponse> createBudget(Authentication authentication, @Valid @RequestBody BudgetRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(budgetService.createBudget(authentication, request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BudgetResponse> updateBudgetById(Authentication authentication, @PathVariable Long id, @Valid @RequestBody BudgetRequest request) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(budgetService.updateBudgetById(authentication, id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBudgetById(Authentication authentication, @PathVariable Long id) {
        budgetService.deleteBudgetById(authentication, id);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
