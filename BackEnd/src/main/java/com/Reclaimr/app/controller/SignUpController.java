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

    //TO-DO : email id exists error resolve
    @Autowired
    UserSignUpService userSignUpService;

    @PostMapping("/signup")
    public ResponseEntity<?> signUpUser(@RequestBody User user) {
        // Check if the body is valid
        if (user == null || user.getName() == null || user.getEnrollmentNumber() == null ||
                user.getEmail() == null || user.getPhoneNumber() == 0) {

            log.error("Invalid user body: {}", user);
            ApiResponse response = new ApiResponse(HttpStatus.BAD_REQUEST , "User could not be signed up" , null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        String result = userSignUpService.saveUserSignedUp(user);
        // checking if the user with this email id already exists
        if ("User with this email already exists".equals(result)) {
            log.error("User with this email already exists : {}" , user.getEmail());
            ApiResponse errorResponse = new ApiResponse(HttpStatus.BAD_REQUEST , result , null);
            return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
        }

        // user created successfully
        log.info("User created successfully: {}", user.getUserId());
        ApiResponse response = new ApiResponse(HttpStatus.CREATED , "User created Successfully" , user);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
