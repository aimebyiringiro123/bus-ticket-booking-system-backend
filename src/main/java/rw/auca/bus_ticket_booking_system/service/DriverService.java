package rw.auca.bus_ticket_booking_system.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import rw.auca.bus_ticket_booking_system.model.Driver;
import rw.auca.bus_ticket_booking_system.repository.DriverRepository;

@Service 
public class DriverService {

    private final DriverRepository driverRepo;

    public DriverService(DriverRepository driverRepo) {
        this.driverRepo = driverRepo;
    }


    public String saveDriver(Driver driver){
        driver.setId(null);
        driverRepo.save(driver);
        return "Driver saved successfully";
    }



    public List <Driver> getAllDrivers(){
        return driverRepo.findAll();
    }
    public Optional<Driver> getDriverById(UUID id){
        if(!driverRepo.existsById(id)){
            return Optional.empty();
        }
        return driverRepo.findById(id); 
    }


    public String  updateDriver(UUID id, Driver driver){
        Optional<Driver> existingDriver = driverRepo.findById(id);
        if (existingDriver.isPresent()) {
            Driver updatedDriver = existingDriver.get();
            updatedDriver.setFirstName(driver.getFirstName());
            updatedDriver.setLastName(driver.getLastName());
            updatedDriver.setLicenseNumber(driver.getLicenseNumber());

            driverRepo.save(updatedDriver);
            return "Driver updated successfully";
        } else {
            return "Driver not found";
        }
    }


    public String deleteDriverById(UUID id){
        if(driverRepo.existsById(id)){
            driverRepo.deleteById(id);
            return "Driver deleted successfully";
        } else {
            return "Driver not found";
        } 
    }
    public String deleteALlDrivers(){
        driverRepo.deleteAll();
        return "All drivers deleted successfully";
    }



}
