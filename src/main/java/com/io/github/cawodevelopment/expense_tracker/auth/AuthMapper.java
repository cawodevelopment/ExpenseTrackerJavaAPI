package com.io.github.cawodevelopment.expense_tracker.auth;

import com.io.github.cawodevelopment.expense_tracker.auth.dto.RegisterRequest;
import com.io.github.cawodevelopment.expense_tracker.auth.dto.UserResponse;
import com.io.github.cawodevelopment.expense_tracker.user.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class AuthMapper {

    public User toUser(RegisterRequest request) {
        User user = new User();

        user.setEmail(request.email());
        user.setPassword(request.password());
        user.setCreatedAt(LocalDate.now());

        return user;
    }

    public UserResponse toUserResponse(User user) {
        return new UserResponse(
                user.getEmail(),
                user.getCreatedAt()
        );
    }
}
