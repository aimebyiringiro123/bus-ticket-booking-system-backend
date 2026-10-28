package rw.auca.bus_ticket_booking_system.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import rw.auca.bus_ticket_booking_system.model.Route;
import rw.auca.bus_ticket_booking_system.repository.RouteRepository;

@Service 
public class RouteService {

    private final RouteRepository routeRepo;

    public RouteService(RouteRepository routeRepo) {
        this.routeRepo = routeRepo;
    }


    

    public String saveRoute(Route route){
        route.setId(null);
        routeRepo.save(route);
        return "Route saved successfully";
    }







    public List <Route> getAllRoutes(){
        return routeRepo.findAll();
    }
    public Optional<Route> getRouteById(UUID id){
        if(!routeRepo.existsById(id)){
            return Optional.empty();
        }
        return routeRepo.findById(id); 
    }







    public String  updateRoute(UUID id, Route route){
        Optional<Route> existingRoute = routeRepo.findById(id);
        if (existingRoute.isPresent()) {
            Route updatedRoute = existingRoute.get();
            updatedRoute.setName(route.getName());

            routeRepo.save(updatedRoute);
            return "Route updated successfully";
        } else {
            return "Route not found";
        }
    }




    public String deleteRouteById(UUID id){
        if(routeRepo.existsById(id)){
            routeRepo.deleteById(id);
            return "Route deleted successfully";
        } else {
            return "Route not found";
        }
    }
    public String deleteAllRoutes(){
        routeRepo.deleteAll();
        return "All routes deleted successfully";
    }

    

    public List<Route> findRouteWithoutDrivers(){
        return routeRepo.findRoutesWithoutDrivers();
    }


}
    



  



