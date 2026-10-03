package com.io.github.cawodevelopment.expense_tracker.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String entityName, Long id) {
        super(entityName + " with id " + id + " not found");
    }

}
