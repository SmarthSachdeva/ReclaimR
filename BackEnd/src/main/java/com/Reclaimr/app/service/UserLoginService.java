package com.Reclaimr.app.service;

import com.Reclaimr.app.models.LoginRequest;
import com.Reclaimr.app.models.User;
import com.Reclaimr.app.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UserLoginService {
    @Autowired
    UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String login(LoginRequest loginRequest){
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        Optional<User> user = userRepository.findByEmail(email);
        if(user.isEmpty()){
            return "User needs to sign up";
        }
        else{
            boolean matches = passwordEncoder.matches(password, user.get().getPassword());
            if(matches){
               log.info("User credentials match");
               log.info("Login Success : {}" , user.get().getEmail());
               return "Login Success";
            }else{
                log.warn("User credentials not match");
                log.warn("Login Failure : {}" , user.get().getEmail());
                return "Login Failure";
            }
        }
    }
}
