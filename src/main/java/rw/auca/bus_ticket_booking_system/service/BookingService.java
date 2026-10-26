package rw.auca.bus_ticket_booking_system.service;

import java.util.UUID;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import rw.auca.bus_ticket_booking_system.model.Booking;
import rw.auca.bus_ticket_booking_system.repository.BookingRepository;



@Service
public class BookingService {

    private final BookingRepository bookingRepo;

    public BookingService(BookingRepository bookingRepo) {
        this.bookingRepo = bookingRepo;
    }


    

    public String saveBooking(Booking booking){
        booking.setId(null);
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



}
