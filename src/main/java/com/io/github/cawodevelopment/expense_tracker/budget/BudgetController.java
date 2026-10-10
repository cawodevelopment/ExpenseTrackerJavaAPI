package com.io.github.cawodevelopment.expense_tracker.budget;

import com.io.github.cawodevelopment.expense_tracker.budget.dto.BudgetRequest;
import com.io.github.cawodevelopment.expense_tracker.budget.dto.BudgetResponse;
import com.io.github.cawodevelopment.expense_tracker.budget.dto.BudgetUpdateRequest;
import com.io.github.cawodevelopment.expense_tracker.category.Category;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@RestController
@RequestMapping("/v1/budgets")
public class BudgetController {

    private final BudgetService budgetService;

    public BudgetController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    @GetMapping
    public ResponseEntity<Page<BudgetResponse>> getBudgets(Authentication authentication,
                                                           @PageableDefault(page = 0, size = 5) Pageable pageable,
                                                           @RequestParam(required = false) Category category,
                                                           @RequestParam(required = false) BigDecimal amount,
                                                           @RequestParam(required = false) LocalDate startDate,
                                                           @RequestParam(required = false) LocalDate endDate) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(budgetService.getBudgets(
                        authentication,
                        pageable,
                        category,
                        amount,
                        startDate,
                        endDate)
                );
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
    public ResponseEntity<BudgetResponse> updateBudgetById(Authentication authentication, @PathVariable Long id, @Valid @RequestBody BudgetUpdateRequest request) {
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
