package com.io.github.cawodevelopment.expense_tracker.auth;

import com.io.github.cawodevelopment.expense_tracker.user.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //Auth business logic
}
