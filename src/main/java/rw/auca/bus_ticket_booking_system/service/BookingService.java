package rw.auca.bus_ticket_booking_system.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import rw.auca.bus_ticket_booking_system.model.Booking;
import rw.auca.bus_ticket_booking_system.model.BookingStatus;
import rw.auca.bus_ticket_booking_system.repository.BookingRepository;



@Service
public class BookingService {

    private final BookingRepository bookingRepo;

    public BookingService(BookingRepository bookingRepo) {
        this.bookingRepo = bookingRepo;
    }


    

    public String saveBooking(Booking booking){

        if(booking.getTravelDate() == null){
            return "booking date is required";
        }

        if(booking.getTravelDate().isBefore(LocalDate.now())){
            return "Booking date cannot be in the past";
        }
        if (booking.getDriver() != null && booking.getDriver().getId() != null
            && bookingRepo.existsByDriverIdAndTravelDateAndStatusNot(
                    booking.getDriver().getId(),
                    booking.getTravelDate(),
                    BookingStatus.CANCELLED)) {
        return "Driver already has a trip on that date";
       }

        booking.setId(null);
        booking.setStatus(BookingStatus.RESERVED);
        bookingRepo.save(booking);
        return "Booking saved successfully";
    }





    public List <Booking> getAllBookings(){
        return bookingRepo.findAll();
    }
    public Optional<Booking> getBookingById(UUID id){
        if(!bookingRepo.existsById(id)){
            return Optional.empty();
        }
        return bookingRepo.findById(id); 
    }





    public String  updateBooking(UUID id, Booking booking){
        Optional<Booking> existingBooking = bookingRepo.findById(id);
        if (existingBooking.isPresent()) {
            Booking updatedBooking = existingBooking.get();
            updatedBooking.setTravelDate(booking.getTravelDate());
            updatedBooking.setSeatNumber(booking.getSeatNumber());
            updatedBooking.setDestination(booking.getDestination());

            bookingRepo.save(updatedBooking);
            return "Booking updated successfully";
        } else {
            return "Booking not found";
        }
    }




    
    public String deleteBookingById(UUID id){
        if(bookingRepo.existsById(id)){
            bookingRepo.deleteById(id);
            return "Booking deleted successfully";
        } else {
            return "Booking not found";
        } 
    }
    public String deleteAllBookings(){
        bookingRepo.deleteAll();
        return "All bookings deleted successfully";
    }


    public List<Booking>getBookingsByStatus(BookingStatus status){
        return bookingRepo.findByStatusOrderByTravelDateAsc(status);
    }


    public List<Booking> getBookingByDate(LocalDate start, LocalDate end){
        return bookingRepo.findByTravelDateBetweenOrderByTravelDateAsc(start, end);
    }



    public String changeStatus(UUID id, BookingStatus newStatus){
        Optional<Booking> existingBooking = bookingRepo.findById(id);
        if(existingBooking.isEmpty()){
            return "Booking with that id does not exit";
        }
        Booking booking = existingBooking.get();
        BookingStatus current = booking.getStatus();

        boolean allowed = false;

        if(current == BookingStatus.RESERVED){
            allowed = newStatus == BookingStatus.PAID || newStatus == BookingStatus.CANCELLED;
        }
        else if(current == BookingStatus.PAID){
            allowed = newStatus == BookingStatus.CANCELLED || newStatus == BookingStatus.BOARDED;
        }

        if(!allowed){
            return "Cannot change status from " + current + " to " + newStatus;
        }

        booking.setStatus(newStatus);
        bookingRepo.save(booking);
        return "Booking status updated successfully";

    }
}
