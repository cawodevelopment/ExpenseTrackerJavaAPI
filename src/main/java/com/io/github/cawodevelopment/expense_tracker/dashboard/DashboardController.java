package com.io.github.cawodevelopment.expense_tracker.dashboard;

import com.io.github.cawodevelopment.expense_tracker.dashboard.dto.SpendingOverTimeResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController("/api/v1/dashboard")
public class DashboardController {

    private DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/spending-by-category")
    public ResponseEntity<BigDecimal> getSpendingByCategory(@RequestParam String category) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(dashboardService.getSpendingByCategory(category));
    }

    @GetMapping("/spending-over-time")
    public ResponseEntity<List<SpendingOverTimeResponse>> getSpendingOverTime() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(dashboardService.getSpendingOverTime());
    }
}
