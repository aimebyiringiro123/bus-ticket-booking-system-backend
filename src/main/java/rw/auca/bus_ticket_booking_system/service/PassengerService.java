package rw.auca.bus_ticket_booking_system.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import rw.auca.bus_ticket_booking_system.model.Passenger;
import rw.auca.bus_ticket_booking_system.repository.PassengerRepository;

@Service 
public class PassengerService {

    private final PassengerRepository passengerRepo;

    public PassengerService(PassengerRepository passengerRepo) {
        this.passengerRepo = passengerRepo;
    }


    //save
    public String savePassenger(Passenger passenger) {
        passenger.setId(null);
        passengerRepo.save(passenger);

        return "Passenger saved successfully";
    }




    //get all
    public List<Passenger> getAllPassengers() {
        return passengerRepo.findAll();
    }




    //find by id
    public Optional<Passenger> getPassengerById(UUID id) {
        return passengerRepo.findById(id);
    }




    //update by id
    public String UpdatePassenger(UUID id, Passenger passenger) {
        Optional<Passenger> existingPassenger = passengerRepo.findById(id);
        if (existingPassenger.isPresent()) {
            Passenger updatedPassenger = existingPassenger.get();
            updatedPassenger.setFirstName(passenger.getFirstName());
            updatedPassenger.setLastName(passenger.getLastName());
            updatedPassenger.setNationalId(passenger.getNationalId());
            updatedPassenger.setPhone(passenger.getPhone());

            passengerRepo.save(updatedPassenger);

            return "Passenger updated successfully";
        } else {
            return "Passenger with that id does not exist";
        }
    }





    //delete by id
    public String deletePassengerById(UUID id) {
        if(!passengerRepo.existsById(id)){
            return "Passenger with that id does not exist";
        } else {
            passengerRepo.deleteById(id);
            return "Passenger deleted successfully";
        }
    }


    

    //delete all
    public String deleteAllPassengers(){
        passengerRepo.deleteAll();
        return "All passengers deleted successfully";
    }

}
