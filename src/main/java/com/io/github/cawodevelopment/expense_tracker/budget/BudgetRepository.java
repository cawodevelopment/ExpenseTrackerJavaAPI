package com.io.github.cawodevelopment.expense_tracker.budget;

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
public interface BudgetRepository extends JpaRepository<Budget, Long> {
  Optional<Budget> findByUser_EmailAndId(String email, Long id);

    @Query("""
        SELECT b
        FROM Budget b
        WHERE b.user.email = :email
          AND (:category IS NULL OR b.category = :category)
          AND (:amount IS NULL OR b.amount = :amount)
          AND (:startDate IS NULL OR b.startDate >= :startDate)
          AND (:endDate IS NULL OR b.endDate <= :endDate)
        """)
        Page<Budget> findAllByEmail(
          @Param("email") String email,
            Pageable pageable,
            @Param("category") Category category,
            @Param("amount") BigDecimal amount,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );
}