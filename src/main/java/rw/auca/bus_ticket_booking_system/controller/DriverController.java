package rw.auca.bus_ticket_booking_system.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rw.auca.bus_ticket_booking_system.model.Driver;
import rw.auca.bus_ticket_booking_system.service.DriverService;




@RestController 
@RequestMapping("/api/drivers")
public class DriverController {

    private final DriverService driverService;

    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }


    @PostMapping ("/save")
    public ResponseEntity<String> saveDriverEntity(@RequestBody Driver driver){
        String message = driverService.saveDriver(driver);
        return ResponseEntity.status(201).body(message);
    }






    @GetMapping("/all")
    public List<Driver> findAll() {
        return driverService.getAllDrivers();
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getDriverById(@PathVariable UUID id){
         Optional<Driver> opt = driverService.getDriverById(id);
         if (opt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The Driver with that id does not exist");
        }
        return ResponseEntity.ok(opt.get());
    }





    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateDriver(@PathVariable UUID id, @RequestBody Driver driver){
        String message = driverService.updateDriver(id, driver);
        if(message.equals("driver updated successfully")){
            return ResponseEntity.ok(message);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }




    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteDriver(@PathVariable UUID id) {
        String message = driverService.deleteDriverById(id);
        if (message.equals("The driver with that id does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
        return ResponseEntity.ok(message);
    }
    @DeleteMapping ("/deleteAll")
    public ResponseEntity<String> deleteAllDrivers(){
        driverService.deleteALlDrivers();
        return ResponseEntity.ok("All drivers deleted successfully");
    }


    @GetMapping("/by-route")
    public ResponseEntity<?> findDriverByRoute(@RequestParam String name){
        List<Driver> drivers = driverService.findDriversByRoute(name);
        if (drivers.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No drivers found for the given route");
        }
        return ResponseEntity.ok(drivers);
    }


    @PutMapping ("/{driverId}/routes/{routeId}")
    public ResponseEntity<?> assignRouteToDriver(@PathVariable UUID driverId, @PathVariable UUID routeId){
        String message  = driverService.assignRouteToDriver(driverId, routeId);
        if (message.equals("Driver not found") || message.equals("Route not found")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);

        } else if (message.equals("Driver is already assigned to this route")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        return ResponseEntity.ok(message);
    }


    @PatchMapping("/{driverId}/bus")
    public ResponseEntity<String> changeBus(@PathVariable UUID driverId, @RequestParam int busNumber) {
    String message = driverService.changeBus(driverId, busNumber);

    if (message.equals("The driver with that id does not exist") || message.equals("The bus with that busNumber does not exist")) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }
    if (message.equals("That bus is taken by another driver")) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
    }
    return ResponseEntity.ok(message);
}
    

}
