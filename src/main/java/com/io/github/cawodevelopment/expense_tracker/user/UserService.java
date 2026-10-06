package com.io.github.cawodevelopment.expense_tracker.user;

import com.io.github.cawodevelopment.expense_tracker.auth.dto.UserResponse;
import com.io.github.cawodevelopment.expense_tracker.user.dto.UserChangePasswordRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponse updateMe(Authentication authentication, UserChangePasswordRequest request) {
        User user = userRepository
                .findByEmail(authentication.getName())
                .orElseThrow(() -> new IllegalStateException("User not found"));

        user.setPassword(passwordEncoder.encode(request.password()));
        userRepository.save(user);

        return userMapper.toUserResponse(user);
    }

    public void deleteMe(Authentication authentication) {
        User user = userRepository
                .findByEmail(authentication.getName())
                .orElseThrow(() -> new IllegalStateException("User not found"));

        userRepository.deleteById(user.getId());
    }
}
