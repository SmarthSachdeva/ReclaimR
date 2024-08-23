package com.Reclaimr.app.repository;

import com.Reclaimr.app.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public interface UserRepository extends MongoRepository<User, String> {
}
