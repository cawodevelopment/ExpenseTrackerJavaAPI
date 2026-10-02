package com.io.github.cawodevelopment.expense_tracker.dashboard;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/v1/dashboard")
public class DashboardController {

    @GetMapping("/summary")
    public String getSummary() {
        return "Summary";
    }

    @GetMapping("/spending-by-category")
    public String getSpendingByCategory() {
        return "Spending by category";
    }

    @GetMapping("/spending-over-time")
    public String getSpendingOverTime() {
        return "Spending over time";
    }
}
