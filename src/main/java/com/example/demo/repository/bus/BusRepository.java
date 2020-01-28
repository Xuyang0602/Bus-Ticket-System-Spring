package com.example.demo.repository.bus;

import com.example.demo.model.bus.Agency;
import com.example.demo.model.bus.Bus;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BusRepository extends MongoRepository<Bus, String> {
    Bus findByCode(String busCode);

    Bus findByCodeAndAgency(String busCode, Agency agency);
}
