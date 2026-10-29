package rw.auca.bus_ticket_booking_system.model;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;



@Entity
@Getter @Setter
@Table(name = "luggage")
public class Luggage {

    @Id 
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String itemName;
    private String description;
    private int weightKg;
    private LocalDate checkedDate;

    @ManyToOne
    @JoinColumn (name = "booking_id")
    private Booking booking;



    




}
