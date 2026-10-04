package com.io.github.cawodevelopment.expense_tracker.user;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //For non-security related business logic
}
