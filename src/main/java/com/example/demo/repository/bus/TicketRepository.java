package com.example.demo.repository.bus;

import com.example.demo.model.bus.Ticket;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TicketRepository extends MongoRepository<Ticket, Long> {
}
