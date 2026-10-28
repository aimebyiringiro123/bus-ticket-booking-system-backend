package rw.auca.bus_ticket_booking_system.repository;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import rw.auca.bus_ticket_booking_system.model.Passenger;

@Repository 
public interface PassengerRepository extends JpaRepository<Passenger, UUID> {

    List<Passenger> findByLastNameIgnoreCaseOrderByFirstNameAsc(String lastName);

    @Query("SELECT DISTINCT b.passenger FROM Booking b WHERE b.driver.id = :driverId")
    List<Passenger> findPassengersByDriverId(@Param("driverId") UUID driverId);
}
