package com.example.demo.repository.bus;

import com.example.demo.model.bus.Agency;
import com.example.demo.model.bus.Bus;
import com.example.demo.model.bus.Stop;
import com.example.demo.model.bus.Trip;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TripRepository extends MongoRepository<Trip, String> {
    Trip findBySourceStopAndDestStopAndBus(Stop source, Stop destination, Bus bus);

    List<Trip> findAllBySourceStopAndDestStop(Stop source, Stop destination);

    List<Trip> findByAgency(Agency agency);
}
