package fr.norsys.gestionReservations.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name="reserations")
@Data
public class Reservation {
    @Id
    @Column(name = "reservation_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reservationId;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @DateTimeFormat
    @FutureOrPresent(message = "Reservation date should be present or future")
    @Column(name="date_reservation")
    private LocalDateTime dateReservation;

    @DateTimeFormat
    @Future(message = "Due date should be in the future")
    @Column(name="due_date")
    private LocalDateTime dueDate;

    public Reservation(Room room, LocalDateTime dateReservation, LocalDateTime dueDate) {
        this.room = room;
        this.dateReservation = dateReservation;
        this.dueDate = dueDate;
    }
    public boolean areDatesCompatible(){
        return dateReservation.isBefore(dueDate) ;
    }

    public Reservation() {
    }
}
