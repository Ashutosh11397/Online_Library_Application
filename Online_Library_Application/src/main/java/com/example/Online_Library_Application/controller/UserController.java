package com.example.Online_Library_Application.controller;

import java.util.List;
import java.util.Optional;

import com.example.Online_Library_Application.DTO.UserDTO.*;
import com.example.Online_Library_Application.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.example.Online_Library_Application.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    // User Registion API
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        if (userService.isUserExists(request.getEmail())) {
            return ResponseEntity.badRequest().body("User already registered with this email.");
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setUserType(request.getUserType());
        user.setMembershipStartDate(request.getMembershipStartDate());
        user.setMembershipMonths(request.getMembershipMonths());

        return ResponseEntity.ok(userService.registerUser(user));
    }


    // User Login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        Optional<User> user = userService.login(request.getEmail(), request.getPassword());

        if (user.isPresent()) {
            User u = user.get();
            LoginResponse response = new LoginResponse(u.getName(), u.getEmail(), u.getMembershipMonths());
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(401).body("Invalid credentials.");
        }
    }

    // 3. Get All Register Users List
    @GetMapping("/getByALLRegisterUser")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    // Check/Update User Membership Validity
    @GetMapping("/{userId}/membership-valid")
    public ResponseEntity<String> checkMembership(@PathVariable Long userId) {
        boolean valid = userService.isMembershipValid(userId);
        if (!valid) {
            return ResponseEntity.ok("Membership expired. User has been updated.");
        }
        return ResponseEntity.ok("Membership is valid.");
    }

    // usermenbership update
    @PutMapping("/{userId}/update-membership")
    public ResponseEntity<String> updateMembership(
            @PathVariable Long userId,
            @RequestBody UpdateMembershipRequest request) {

        boolean isUpdated = userService.updateMembership(userId, request);

        if (isUpdated) {
            return ResponseEntity.ok("User membership updated successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Membership is still valid or update failed.");
        }
    }

}