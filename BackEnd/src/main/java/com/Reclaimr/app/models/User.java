package com.Reclaimr.app.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "users")
public class User {
    @Id
    private String userId;  // Use String for MongoDB IDs
    private String name;
    private String email;
    private long phoneNumber;
    private String enrollmentNumber;
    private String password;
}
