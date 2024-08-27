package com.Reclaimr.app.models;

import jakarta.persistence.Entity;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "lost-found-items")
public class LnFItems {
    @Id
    String id;
    String name;
    String description;
    String imageLink;

    @DBRef
    private User user;

}
