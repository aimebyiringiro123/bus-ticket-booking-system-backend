package rw.auca.bus_ticket_booking_system.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import rw.auca.bus_ticket_booking_system.model.Booking;
import rw.auca.bus_ticket_booking_system.model.BookingStatus;
import rw.auca.bus_ticket_booking_system.model.Luggage;
import rw.auca.bus_ticket_booking_system.repository.BookingRepository;
import rw.auca.bus_ticket_booking_system.repository.LuggageRepository;

@Service 
public class LuggageService {

    private final LuggageRepository luggageRepository;
    private final BookingRepository bookingRepo;

    public LuggageService(LuggageRepository luggageRepository, BookingRepository bookingRepo) {
        this.luggageRepository = luggageRepository;
        this.bookingRepo = bookingRepo;
    }




    public String saveLuggage(UUID bookingId, Luggage luggage){
        Optional<Booking> opt = bookingRepo.findById(bookingId);
        if(opt.isEmpty()){
            return "booking with that id does not exist";
        }
        Booking booking = opt.get();

        if(booking.getStatus() != BookingStatus.BOARDED){
            return "Luggage items are only for BOARDED bookings";
        }

        luggage.setId(null);
        luggage.setBooking(booking); 
        luggageRepository.save(luggage);
        return "Luggage saved successfully";

    }


    public List<Luggage> findAll() {
        return luggageRepository.findAll();
    }

    public List<Luggage> findHistory(String nationalId) {
        return luggageRepository.findHistoryByNationalId(nationalId);
    }



    public String delete(UUID id) {
        if (!luggageRepository.existsById(id)) {
            return "The luggage with that id does not exist";
        }
        luggageRepository.deleteById(id);
        return "Luggage deleted successfully";
    }


}
