package com.Reclaimr.app.repository;

import com.Reclaimr.app.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByEmail(String s);

    Optional<User> findUser(String userId);
}
