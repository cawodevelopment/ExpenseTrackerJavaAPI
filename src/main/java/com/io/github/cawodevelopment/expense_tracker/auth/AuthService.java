package com.io.github.cawodevelopment.expense_tracker.auth;

import com.io.github.cawodevelopment.expense_tracker.auth.dto.RegisterRequest;
import com.io.github.cawodevelopment.expense_tracker.auth.dto.UserResponse;
import com.io.github.cawodevelopment.expense_tracker.user.User;
import com.io.github.cawodevelopment.expense_tracker.user.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final AuthMapper authMapper;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, AuthMapper authMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.authMapper = authMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponse registerUser(RegisterRequest request) {
        User user = new User();

        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setCreatedAt(LocalDate.now());

        userRepository.save(user);

        return authMapper.toUserResponse(user);
    }

}
