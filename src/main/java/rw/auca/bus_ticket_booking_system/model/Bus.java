package rw.auca.bus_ticket_booking_system.model;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@Getter @Setter @NoArgsConstructor 
@Table(name = "bus")
public class Bus {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private int busNumber;
    
    private String plateNumber; 


    
}
