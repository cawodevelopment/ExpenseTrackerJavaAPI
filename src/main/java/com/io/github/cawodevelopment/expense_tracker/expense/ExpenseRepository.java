package com.io.github.cawodevelopment.expense_tracker.expense;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExpenseRepository  extends JpaRepository<Expense, Long> {
    Page<Expense> findAllByUsername(String username, Pageable pageable);

    Optional<Expense> findByUsernameAndId(String username, Long id);
}
