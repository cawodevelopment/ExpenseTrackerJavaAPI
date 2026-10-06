package com.io.github.cawodevelopment.expense_tracker.budget;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long> {
    Page<Budget> findAllByUsername(String username, Pageable pageable);

    Optional<Budget> findByUsernameAndId(String username, Long id);
}
