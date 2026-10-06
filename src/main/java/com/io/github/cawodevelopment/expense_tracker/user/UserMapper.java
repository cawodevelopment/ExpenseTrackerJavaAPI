package com.io.github.cawodevelopment.expense_tracker.user;

import com.io.github.cawodevelopment.expense_tracker.auth.dto.UserResponse;
import com.io.github.cawodevelopment.expense_tracker.user.dto.UserChangePasswordRequest;

public class UserMapper {

    public UserResponse toUserResponse(User user) {
        return new UserResponse(
                user.getEmail(),
                user.getCreatedAt()
        );
    }
}
