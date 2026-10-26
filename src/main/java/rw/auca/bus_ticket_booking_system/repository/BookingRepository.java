package rw.auca.bus_ticket_booking_system.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import rw.auca.bus_ticket_booking_system.model.Booking;

public interface BookingRepository extends JpaRepository<Booking, UUID>{

}
