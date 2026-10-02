package com.io.github.cawodevelopment.expense_tracker.budget;

import com.io.github.cawodevelopment.expense_tracker.budget.dto.BudgetResponse;
import com.io.github.cawodevelopment.expense_tracker.budget.dto.BudgetRequest;
import org.springframework.stereotype.Component;

@Component
public class BudgetMapper {

    public Budget toBudgetEntity(BudgetRequest request) {
        Budget budget = new Budget();

        budget.setAmount(request.amount());
        budget.setDescription(request.description());
        budget.setCategory(request.category());
        budget.setStartDate(request.startDate());
        budget.setEndDate(request.endDate());

        return budget;
    }

    public BudgetResponse toBudgetResponse(Budget budget) {
        return new BudgetResponse(
                budget.getId(),
                budget.getDescription(),
                budget.getCategory(),
                budget.getAmount(),
                budget.getStartDate(),
                budget.getEndDate(),
                budget.getCreatedAt()
        );

    }


}
