package com.io.github.cawodevelopment.expense_tracker.budget;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long> {
    List<Budget> findAllByUsername(String username);

    Optional<Budget> findByUsernameAndId(String username, Long id);
}
