package rw.auca.bus_ticket_booking_system.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rw.auca.bus_ticket_booking_system.model.Luggage;

@Repository 
public interface LuggageRepository  extends JpaRepository<Luggage, UUID> {

    

}
