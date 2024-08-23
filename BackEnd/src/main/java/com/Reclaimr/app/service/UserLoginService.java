package com.Reclaimr.app.service;

import com.Reclaimr.app.models.LoginRequest;
import com.Reclaimr.app.models.User;
import com.Reclaimr.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UserLoginService {
    @Autowired
    UserRepository userRepository;

    public String login(LoginRequest loginRequest){
        String email = loginRequest.getEmail();
        
    }
}
