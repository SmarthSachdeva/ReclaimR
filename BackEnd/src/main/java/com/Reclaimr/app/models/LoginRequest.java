package com.Reclaimr.app.models;

import lombok.Data;

@Data
public class LoginRequest {
    String email;
    String password;

    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

}
