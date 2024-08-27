package com.Reclaimr.app.models;

import jakarta.persistence.UniqueConstraint;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "users")
public class User {
    @Id
    private String userId;
    private String name;
    @Indexed(unique = true)
    private String email;
    private long phoneNumber;
    private String enrollmentNumber;
    private String password;
}
