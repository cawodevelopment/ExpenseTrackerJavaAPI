package com.io.github.cawodevelopment.expense_tracker.expense;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExpenseRepository  extends JpaRepository<Expense, Long> {
    List<Expense> findAllByUsername(String username);

    Optional<Expense> findByUsernameAndId(String username, Long id);
}
