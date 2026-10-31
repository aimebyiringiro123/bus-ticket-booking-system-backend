package rw.auca.bus_ticket_booking_system.service;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import rw.auca.bus_ticket_booking_system.model.BookingStatus;
import rw.auca.bus_ticket_booking_system.repository.BookingRepository;
import rw.auca.bus_ticket_booking_system.repository.BusRepository;
import rw.auca.bus_ticket_booking_system.repository.DriverRepository;
import rw.auca.bus_ticket_booking_system.repository.LuggageRepository;
import rw.auca.bus_ticket_booking_system.repository.PassengerRepository;

@Service
public class DashboardService {

    private final PassengerRepository passengerRepo;
    private final DriverRepository driverRepo;
    private final BusRepository busRepo;
    private final BookingRepository bookingRepo;
    private final LuggageRepository luggageRepo;

    public DashboardService(PassengerRepository passengerRepo,
                            DriverRepository driverRepo,
                            BusRepository busRepo,
                            BookingRepository bookingRepo,
                            LuggageRepository luggageRepo) {
        this.passengerRepo = passengerRepo;
        this.driverRepo = driverRepo;
        this.busRepo = busRepo;
        this.bookingRepo = bookingRepo;
        this.luggageRepo = luggageRepo;
    }

    public Map<String, Long> summary() {
        Map<String, Long> result = new LinkedHashMap<>();
        result.put("passengers", passengerRepo.count());
        result.put("drivers", driverRepo.count());
        result.put("freeBuses", (long) busRepo.findBusesWithoutDrivers().size());
        result.put("tripsToday", bookingRepo.countByTravelDate(LocalDate.now()));
        result.put("reserved", bookingRepo.countByStatus(BookingStatus.RESERVED));
        result.put("luggageItems", luggageRepo.count());
        return result;
    }
}