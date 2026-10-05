package com.io.github.cawodevelopment.expense_tracker.expense;

import com.io.github.cawodevelopment.expense_tracker.expense.dto.ExpenseRequest;
import com.io.github.cawodevelopment.expense_tracker.expense.dto.ExpenseResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/v1/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping
    public ResponseEntity<List<ExpenseResponse>> getExpenses(Authentication authentication) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(expenseService.getExpenses(authentication));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExpenseResponse> getExpenseById(Authentication authentication, @PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(expenseService.getExpenseById(authentication, id));
    }

    @PostMapping
    public ResponseEntity<ExpenseResponse> createExpense(Authentication authentication, @Valid @RequestBody ExpenseRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(expenseService.createExpense(authentication, request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExpenseResponse> updateExpenseById(Authentication authentication, @PathVariable Long id, @Valid @RequestBody ExpenseRequest request) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(expenseService.updateExpenseById(authentication, id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpenseById(Authentication authentication, @PathVariable Long id) {
        expenseService.deleteExpenseById(authentication, id);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
