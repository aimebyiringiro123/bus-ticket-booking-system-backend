package rw.auca.bus_ticket_booking_system.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import rw.auca.bus_ticket_booking_system.model.Bus;
import rw.auca.bus_ticket_booking_system.repository.BusRepository;

@Service 
public class BusService {

    private final BusRepository busRepo;

    public BusService(BusRepository busRepo) {
        this.busRepo = busRepo;
    }


    

    public String saveBus(Bus bus){
        bus.setId(null);
        busRepo.save(bus);
        return "Bus saved successfully";
    }






    public List <Bus> getAllBuses(){
        return busRepo.findAll();
    }
    public Optional<Bus> getBusById(UUID id){
        if(!busRepo.existsById(id)){
            return Optional.empty();
        }
        return busRepo.findById(id); 
    }






    public String  updateBus(UUID id, Bus bus){
        Optional<Bus> existingBus = busRepo.findById(id);
        if (existingBus.isPresent()) {
            Bus updatedBus = existingBus.get();
            updatedBus.setBusNumber(bus.getBusNumber());
            updatedBus.setPlateNumber(bus.getPlateNumber());

            busRepo.save(updatedBus);
            return "Bus updated successfully";
        } else {
            return "Bus not found";
        }
    }




    public String deleteBusById(UUID id){
        if(busRepo.existsById(id)){
            busRepo.deleteById(id);
            return "Bus deleted successfully";
        } else {
            return "Bus not found";
        } 
    }
    public String deleteAllBuses(){
        busRepo.deleteAll();
        return "All buses deleted successfully";
    }



}
