package com.example.demo.repository.bus;

import com.example.demo.model.bus.Stop;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StopRepository extends MongoRepository<Stop, String> {
    Stop findByCode(String code);
}
