package com.io.github.cawodevelopment.expense_tracker.expense;

import com.io.github.cawodevelopment.expense_tracker.expense.dto.ExpenseRequest;
import com.io.github.cawodevelopment.expense_tracker.expense.dto.ExpenseResponse;
import com.io.github.cawodevelopment.expense_tracker.expense.dto.ExpenseUpdateRequest;
import org.springframework.stereotype.Component;

@Component
public class ExpenseMapper {
    public Expense toExpenseEntity(ExpenseRequest request) {
        Expense expense = new Expense();

        expense.setDescription(request.description());
        expense.setCategory(request.category());
        expense.setAmount(request.amount());
        expense.setDate(request.date());

        return expense;
    }

    public ExpenseResponse toExpenseResponse(Expense expense) {
        return new ExpenseResponse(
                expense.getId(),
                expense.getDescription(),
                expense.getCategory(),
                expense.getAmount(),
                expense.getDate(),
                expense.getCreatedAt()
        );
    }

    public void updateExpenseFromRequest(Expense expense, ExpenseUpdateRequest request) {
        expense.setDescription(request.description());
        expense.setCategory(request.category());
        expense.setAmount(request.amount());
        expense.setDate(request.date());
    }
}
