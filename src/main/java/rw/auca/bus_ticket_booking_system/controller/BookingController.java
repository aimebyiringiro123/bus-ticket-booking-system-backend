package rw.auca.bus_ticket_booking_system.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rw.auca.bus_ticket_booking_system.model.Booking;
import rw.auca.bus_ticket_booking_system.service.BookingService;

@RestController 
@RequestMapping ("/api/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }


    @PostMapping ("/save")
    public ResponseEntity<String> saveBooking(@RequestBody Booking booking){
        String message = bookingService.saveBooking(booking);
        return ResponseEntity.status(201).body(message);
    }






    @GetMapping("/all")
    public List<Booking> findAll() {
        return bookingService.getAllBookings();
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getBookingById(@PathVariable UUID id){
        Optional<Booking> opt = bookingService.getBookingById(id);

         if (opt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The booking with that id does not exist");
        }
        return ResponseEntity.ok(opt.get());
    }





    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateBooking(@PathVariable UUID id, @RequestBody Booking booking){
        String message = bookingService.updateBooking(id, booking);
        if(message.equals("Booking updated successfully")){
            return ResponseEntity.ok(message);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }




    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBooking(@PathVariable UUID id) {
        String message = bookingService.deleteBookingById(id);
        if (message.equals("The booking with that id does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
        return ResponseEntity.ok(message);
    }
    @DeleteMapping ("/deleteAll")
    public ResponseEntity<String> deleteAllBookings(){
        bookingService.deleteAllBookings();
        return ResponseEntity.ok("All bookings deleted successfully");
    }


}
