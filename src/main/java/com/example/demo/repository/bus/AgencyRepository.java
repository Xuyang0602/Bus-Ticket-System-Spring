package com.example.demo.repository.bus;

import com.example.demo.model.bus.Agency;
import com.example.demo.model.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AgencyRepository extends MongoRepository<Agency, String> {
    Agency findByCode(String agencyCode);

    Agency findByOwner(User owner);

    Agency findByName(String name);
}
