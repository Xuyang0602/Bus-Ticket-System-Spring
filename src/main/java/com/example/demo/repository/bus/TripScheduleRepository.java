package com.example.demo.repository.bus;

import com.example.demo.model.bus.Trip;
import com.example.demo.model.bus.TripSchedule;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TripScheduleRepository extends MongoRepository<TripSchedule, String> {
    TripSchedule findByTripDetailAndTripDate(Trip tripDetail, String tripDate);
}
