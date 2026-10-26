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

import rw.auca.bus_ticket_booking_system.model.Bus;
import rw.auca.bus_ticket_booking_system.service.BusService;

@RestController 
@RequestMapping ("/api/buses")
public class BusController {

    private final BusService busService;

    public BusController(BusService busService) {
        this.busService = busService;
    }


    @PostMapping ("/save")
    public ResponseEntity<String> saveBus(@RequestBody Bus bus){
        String message = busService.saveBus(bus);
        return ResponseEntity.status(201).body(message);
    }






    @GetMapping("/all")
    public List<Bus> findAll() {
        return busService.getAllBuses();
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getBusById(@PathVariable UUID id){
        Optional<Bus> opt = busService.getBusById(id);

         if (opt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The bus with that id does not exist");
        }
        return ResponseEntity.ok(opt.get());
    }





    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateBus(@PathVariable UUID id, @RequestBody Bus bus){
        String message = busService.updateBus(id, bus);
        if(message.equals("Bus updated successfully")){
            return ResponseEntity.ok(message);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }




    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBus(@PathVariable UUID id) {
        String message = busService.deleteBusById(id);
        if (message.equals("The bus with that id does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
        return ResponseEntity.ok(message);
    }
    @DeleteMapping ("/deleteAll")
    public ResponseEntity<String> deletedeAllBuses(){
        busService.deleteAllBuses();
        return ResponseEntity.ok("All buses deleted successfully");
    }


}
