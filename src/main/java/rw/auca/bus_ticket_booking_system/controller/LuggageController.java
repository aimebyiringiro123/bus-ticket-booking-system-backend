package rw.auca.bus_ticket_booking_system.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rw.auca.bus_ticket_booking_system.model.Luggage;
import rw.auca.bus_ticket_booking_system.service.LuggageService;



@RestController
@RequestMapping("/api/luggage-items")
public class LuggageController {

   private final LuggageService luggageService;

    public LuggageController(LuggageService luggageService) {
        this.luggageService = luggageService;
    }

    @PostMapping("/save")

     public ResponseEntity<String> save(@RequestParam UUID bookingId,
                                       @RequestBody Luggage luggage) {
        String message = luggageService.saveLuggage(bookingId, luggage);

        if (message.equals("The booking with that id does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
        if (message.equals("Luggage items are only for BOARDED bookings")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }


    @GetMapping("/all")
    public List<Luggage> findAll() {
        return luggageService.findAll();
    }

}
