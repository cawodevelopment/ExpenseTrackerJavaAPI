package com.io.github.cawodevelopment.expense_tracker.user;

import com.io.github.cawodevelopment.expense_tracker.auth.dto.UserResponse;
import com.io.github.cawodevelopment.expense_tracker.user.dto.UserChangePasswordRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserResponse updateMe(Authentication authentication, UserChangePasswordRequest request) {
        User user = userRepository
                .findByUsername(authentication.getName())
                .orElseThrow(() -> new IllegalStateException("User not found"));

        user.setPassword(request.password());

        return userMapper.toUserResponse(user);
    }

    public void deleteMe(Authentication authentication) {
        User user = userRepository
                .findByUsername(authentication.getName())
                .orElseThrow(() -> new IllegalStateException("User not found"));

        userRepository.deleteById(user.getId());
    }
}
