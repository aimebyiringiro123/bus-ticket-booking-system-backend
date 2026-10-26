package rw.auca.bus_ticket_booking_system.repository;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import rw.auca.bus_ticket_booking_system.model.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, UUID> {

}
