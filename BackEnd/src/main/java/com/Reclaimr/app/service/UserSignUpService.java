package com.Reclaimr.app.service;

import com.Reclaimr.app.models.User;
import com.Reclaimr.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserSignUpService {

    @Autowired
    private UserRepository userRepository;

    public void saveUserSignedUp(User user){
        userRepository.save(user);
    }
}
