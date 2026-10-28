package rw.auca.bus_ticket_booking_system.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import rw.auca.bus_ticket_booking_system.model.Bus;

@Repository
public interface BusRepository extends JpaRepository<Bus, UUID> {

    @Query("SELECT b FROM Bus b " +
       "WHERE NOT EXISTS (SELECT d FROM Driver d WHERE d.bus = b) " +
       "ORDER BY b.busNumber")
    public List<Bus> findBusesWithoutDrivers();

}
