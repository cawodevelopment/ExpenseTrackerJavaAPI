package com.io.github.cawodevelopment.expense_tracker.expense;

import com.io.github.cawodevelopment.expense_tracker.category.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface ExpenseRepository  extends JpaRepository<Expense, Long> {
    @Query("""
    SELECT e
    FROM Expense e
    WHERE e.user.username = :username
      AND (:category IS NULL OR e.category = :category)
      AND (:minAmount IS NULL OR e.amount >= :minAmount)
      AND (:maxAmount IS NULL OR e.amount <= :maxAmount)
      AND (:date IS NULL OR e.date = :date)
""")
    Page<Expense> findAllByUsername(
            @Param("username") String username,
            Pageable pageable,
            @Param("category") Category category,
            @Param("minAmount") BigDecimal minAmount,
            @Param("maxAmount") BigDecimal maxAmount,
            @Param("date") LocalDate date
    );

    Optional<Expense> findByUsernameAndId(String username, Long id);
}
