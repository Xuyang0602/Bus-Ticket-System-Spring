package com.example.demo.repository.user;

import com.example.demo.model.user.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<Role, String> {
    Role findByRole(String role);
}
