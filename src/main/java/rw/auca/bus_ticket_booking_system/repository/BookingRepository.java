package rw.auca.bus_ticket_booking_system.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import rw.auca.bus_ticket_booking_system.model.Booking;
import rw.auca.bus_ticket_booking_system.model.BookingStatus;

@Repository 
public interface BookingRepository extends JpaRepository<Booking, UUID>{

    List<Booking>findByStatusOrderByTravelDateAsc(BookingStatus status);

    List <Booking> findByTravelDateBetweenOrderByTravelDateAsc(LocalDate start, LocalDate end);

    boolean existsByDriverIdAndTravelDateAndStatusNot(UUID driverId, LocalDate date, BookingStatus status);

    @Query("SELECT b.status, COUNT(b) FROM Booking b GROUP BY b.status")
    List<Object[]> countByStatus();



    @Modifying(clearAutomatically = true)
    @Query("UPDATE Booking b SET b.status = :newStatus " +
       "WHERE b.driver.id = :driverId AND b.travelDate = :date " +
       "AND b.status <> :keepStatus")

       int cancelDay(@Param("driverId") UUID driverId,
              @Param("date") LocalDate date,
              @Param("newStatus") BookingStatus newStatus,
              @Param("keepStatus") BookingStatus keepStatus);

              
              
    
    long countByStatus(BookingStatus status);

    long countByTravelDate(LocalDate date);
    

}
