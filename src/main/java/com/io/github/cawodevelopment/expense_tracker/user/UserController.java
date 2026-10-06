package com.io.github.cawodevelopment.expense_tracker.user;

import com.io.github.cawodevelopment.expense_tracker.auth.dto.UserResponse;
import com.io.github.cawodevelopment.expense_tracker.expense.dto.ExpenseRequest;
import com.io.github.cawodevelopment.expense_tracker.user.dto.UserChangePasswordRequest;
import com.io.github.cawodevelopment.expense_tracker.user.dto.UserRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping("/changePassword")
    public ResponseEntity<UserResponse> updateMe(Authentication authentication, @Valid @RequestBody UserChangePasswordRequest request) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.updateMe(authentication, request));
    }

    @DeleteMapping("/me")
    public ResponseEntity<Void> deleteMe(Authentication authentication) {
        userService.deleteMe(authentication);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
