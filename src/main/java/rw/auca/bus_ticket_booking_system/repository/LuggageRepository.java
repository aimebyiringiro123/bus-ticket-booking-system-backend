package rw.auca.bus_ticket_booking_system.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import rw.auca.bus_ticket_booking_system.model.Luggage;

@Repository 
public interface LuggageRepository  extends JpaRepository<Luggage, UUID> {

    @Query("SELECT l FROM Luggage l " +
           "WHERE l.booking.passenger.nationalId = :nationalId " +
           "ORDER BY l.checkedDate DESC")
    List<Luggage> findHistoryByNationalId(@Param("nationalId") String nationalId);
    
    boolean existsByBookingId(UUID bookingId);
}
