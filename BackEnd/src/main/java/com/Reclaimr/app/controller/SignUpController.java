package com.Reclaimr.app.controller;

import com.Reclaimr.app.models.ApiResponse;
import com.Reclaimr.app.models.User;
import com.Reclaimr.app.service.UserSignUpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/user")
public class SignUpController {

    @Autowired
    private UserSignUpService userSignUpService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse> signUpUser(@RequestBody User user) {
        // Validate the request body
        if (user == null || user.getName() == null || user.getEnrollmentNumber() == null ||
                user.getEmail() == null || user.getPhoneNumber() == 0) {

            log.error("Invalid user body: {}", user);
            ApiResponse response = new ApiResponse(HttpStatus.BAD_REQUEST, "User could not be signed up. Invalid data.", null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        // Attempt to save the new user
        String result = userSignUpService.saveUserSignedUp(user);

        // Handle duplicate email scenario
        if ("User with this email already exists".equals(result)) {
            log.error("User with this email already exists: {}", user.getEmail());
            ApiResponse errorResponse = new ApiResponse(HttpStatus.CONFLICT, result, null);
            return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
        }

        // User creation successful
        log.info("User created successfully: {}", user.getUserId());
        ApiResponse response = new ApiResponse(HttpStatus.CREATED, "User registered successfully", user);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
