package rw.auca.bus_ticket_booking_system.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rw.auca.bus_ticket_booking_system.model.Driver;

public interface DriverRepository extends JpaRepository<Driver, UUID>{

    @Query("SELECT DISTINCT d FROM Driver d JOIN d.routes r " +
       "WHERE LOWER(r.name) LIKE LOWER(CONCAT('%', :name, '%'))")

    List<Driver> findDriversByRouteName(@Param("name") String name);

            

}
