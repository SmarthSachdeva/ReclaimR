package com.Reclaimr.app.controller;

import com.Reclaimr.app.models.ApiResponse;
import com.Reclaimr.app.models.LoginRequest;
import com.Reclaimr.app.service.UserLoginService;
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
public class LoginController {
    @Autowired
    UserLoginService userLoginService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        // checking if login request is valid or not
        if(loginRequest==null){
            log.error("Invalid login request");
            ApiResponse errorResponse = new ApiResponse(HttpStatus.BAD_REQUEST , "Invalid login request" , null);
            return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
        }
        if(loginRequest.getEmail()==null || loginRequest.getPassword()==null){
            log.error("Incomplete login request");
            ApiResponse errorResponse = new ApiResponse(HttpStatus.BAD_REQUEST , "Invalid login" , null);
            return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
        }

        userLoginService.login(loginRequest);

    }


}
