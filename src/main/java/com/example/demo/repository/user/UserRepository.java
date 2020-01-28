package com.example.demo.repository.user;

import com.example.demo.model.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    User findByEmail(String email);
}
