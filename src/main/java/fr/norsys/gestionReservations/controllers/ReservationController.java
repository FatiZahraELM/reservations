package fr.norsys.gestionReservations.controllers;

import fr.norsys.gestionReservations.entities.Reservation;
import fr.norsys.gestionReservations.entities.Room;
import fr.norsys.gestionReservations.exceptions.AlreadyUsedException;
import fr.norsys.gestionReservations.exceptions.IllegalDatesFormatException;
import fr.norsys.gestionReservations.exceptions.ResourceNotFoundException;
import fr.norsys.gestionReservations.repositories.RoomRepository;
import fr.norsys.gestionReservations.services.ReservationService;
import fr.norsys.gestionReservations.services.RoomService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/reservations")

public class ReservationController {

    @Autowired
    private ReservationService reservationService;
    @Autowired
    private RoomService roomService ;


    @GetMapping
    public ResponseEntity<List<Reservation>> findAll(){
        List<Reservation> reservations=reservationService.getAll();
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> findById(@PathVariable int id) {
        Reservation reservation = reservationService.findById(id);
        if(reservation==null)
            throw new ResourceNotFoundException("Reservation with id {" + id + "} does not exist");

        return ResponseEntity.ok().body(reservation);
    }

    @PostMapping
    public ResponseEntity<Void> createReservation(@RequestBody @Valid Reservation reservation){
        Reservation reservation1=reservationService.findById(reservation.getReservationId());
        if(!roomService.findAvailableRoom().contains(reservation.getRoom()))
            throw new AlreadyUsedException("the room is not available");
        if(!reservation.areDatesCompatible())
            throw new IllegalDatesFormatException("Reservation date should be before due date");

        if(reservation1!=null) {
            throw new AlreadyUsedException("Reservation with id: {" + reservation1.getReservationId() + "} already exists");
        }
        reservationService.save(reservation);
        return ResponseEntity.ok(null);
    }


    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public  ResponseEntity<Reservation> updateReservation(@PathVariable int id,@RequestBody @Valid Reservation reservationDetails) {
        Reservation updatedReservation=reservationService.findById(id);
        if(updatedReservation==null)
            throw new ResourceNotFoundException("Reservation with id {" + id + "} does not exist");

        reservationService.updateReservation(id,reservationDetails);
        return ResponseEntity.ok().body(updatedReservation);
    }
    @GetMapping("/room/available")
    public List<Room> getAvailableRooms() {
        return roomService.findAvailableRoom();
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable int id){
        Reservation reservation = reservationService.findById(id);
        if(reservation==null)
            throw new ResourceNotFoundException("Reservation with id {" + id + "} does not exist");
        reservationService.deleteById(id);
        return  ResponseEntity.ok(null);
    }


}
