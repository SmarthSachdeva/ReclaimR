package com.Reclaimr.app.controller;

import com.Reclaimr.app.models.User;
import com.Reclaimr.app.service.UserSignUpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/v1/user")
public class SignUpController {

    @Autowired
    private UserSignUpService userSignUpService;

    @PostMapping("/signup")
    public ResponseEntity<?> signUpUser(@RequestBody User user) {
        // Check if the body is valid
        if (user == null || user.getName() == null || user.getEnrollmentNumber() == null ||
                user.getEmail() == null || user.getPhoneNumber() == 0) {

            log.error("Invalid user body: {}", user);

            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("status", "error");
            errorResponse.put("message", "User body is invalid or missing required fields");

            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }

        // Save the new user
        userSignUpService.saveUserSignedUp(user);

        log.info("User created successfully: {}", user);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}
