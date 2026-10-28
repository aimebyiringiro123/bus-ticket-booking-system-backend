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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rw.auca.bus_ticket_booking_system.model.Passenger;
import rw.auca.bus_ticket_booking_system.service.PassengerService;

@RestController 
@RequestMapping ("/api/passengers")
public class PassengerController {


    private final PassengerService passengerService;

    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }


    @PostMapping ("/save")
    public ResponseEntity<String> savePassenger(@RequestBody Passenger passenger){
        String message = passengerService.savePassenger(passenger);
        return ResponseEntity.status(201).body(message);
    }






    @GetMapping("/all")
    public List<Passenger> findAll() {
        return passengerService.getAllPassengers();
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getPassengerById(@PathVariable UUID id){
        Optional<Passenger> opt = passengerService.getPassengerById(id);

         if (opt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The passenger with that id does not exist");
        }
        return ResponseEntity.ok(opt.get());
    }





    @PutMapping("/update/{id}")
    public ResponseEntity<String> updatePassenger(@PathVariable UUID id, @RequestBody Passenger passenger){
        String message = passengerService.UpdatePassenger(id, passenger);
        if(message.equals("Passenger updated successfully")){
            return ResponseEntity.ok(message);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }




    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = passengerService.deletePassengerById(id);
        if (message.equals("The passenger with that id does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
        return ResponseEntity.ok(message);
    }
    @DeleteMapping ("/deleteAll")
    public ResponseEntity<String> deleteAllPassengers(){
        passengerService.deleteAllPassengers();
        return ResponseEntity.ok("All passengers deleted successfully");
    }



    @GetMapping("/by-last-name")
    public List<Passenger> byLastName(@RequestParam String lastName) {
         return passengerService.findByLastname(lastName);
    }

    @GetMapping("/of-driver/{driverId}")
    public ResponseEntity<?> getPassengerByDriverId(@PathVariable UUID driverId){
        List<Passenger> passengers = passengerService.findPassengersByDriverId(driverId);
        if(passengers.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No passengers found for the given driver ID");
        }
        return ResponseEntity.ok(passengers);
    }

    

}
