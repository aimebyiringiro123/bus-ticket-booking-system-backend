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

import rw.auca.bus_ticket_booking_system.model.Route;
import rw.auca.bus_ticket_booking_system.service.RouteService;

@RestController 
@RequestMapping ("/api/routes")
public class RouteController {

    private final RouteService routeService;

    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }


    @PostMapping ("/save")
    public ResponseEntity<String> saveRoute(@RequestBody Route route){
        String message = routeService.saveRoute(route);
        return ResponseEntity.status(201).body(message);
    }






    @GetMapping("/all")
    public List<Route> findAll() {
        return routeService.getAllRoutes();
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getRouteById(@PathVariable UUID id){
        Optional<Route> opt = routeService.getRouteById(id);

         if (opt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The route with that id does not exist");
        }
        return ResponseEntity.ok(opt.get());
    }





    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateRoute(@PathVariable UUID id, @RequestBody Route route){
        String message = routeService.updateRoute(id, route);
        if(message.equals("Route updated successfully")){
            return ResponseEntity.ok(message);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }




    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = routeService.deleteRouteById(id);
        if (message.equals("The route with that id does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
        return ResponseEntity.ok(message);
    }
    @DeleteMapping ("/deleteAll")
    public ResponseEntity<String> deleteAll(){
        routeService.deleteAllRoutes();
        return ResponseEntity.ok("All routes deleted successfully");
    }


}
