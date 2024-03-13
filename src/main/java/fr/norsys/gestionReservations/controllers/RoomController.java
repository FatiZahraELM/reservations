package fr.norsys.gestionReservations.controllers;

import fr.norsys.gestionReservations.entities.Room;
import fr.norsys.gestionReservations.exceptions.AlreadyUsedException;
import fr.norsys.gestionReservations.exceptions.ResourceNotFoundException;
import fr.norsys.gestionReservations.services.ReservationService;
import fr.norsys.gestionReservations.services.RoomService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;
    private ReservationService reservationService;

    @GetMapping
    public ResponseEntity<List<Room>> findAll(){
        List<Room> rooms=roomService.getAll();
        return ResponseEntity.ok(rooms);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Room> findById(@PathVariable int id) {
        Room room = roomService.findById(id);
        if(room==null)
            throw new ResourceNotFoundException("room with id {" + id + "} does not exist");

        return ResponseEntity.ok().body(room);
    }


    @PostMapping
    public ResponseEntity<Void> createRoom(@RequestBody @Valid Room room){
        Room room1=roomService.findById(room.getRoomId());
        if(room1!=null) {
            throw new AlreadyUsedException("Room with id: {" + room1.getRoomId() + "} already exists");
        }
        roomService.save(room);
        return ResponseEntity.ok(null);
    }


    @PutMapping("/{id}")
    public  ResponseEntity<Room> updateRoom(@PathVariable int id,@RequestBody @Valid Room roomDetails) {
        Room updatedRoom=roomService.findById(id);
        if(updatedRoom==null)
            throw new ResourceNotFoundException("Room with id {" + id + "} does not exist");

        roomService.updateRoom(id,roomDetails);
        return ResponseEntity.ok().body(updatedRoom);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoom(@PathVariable int id){
        Room room = roomService.findById(id);
        if(room==null)
            throw new ResourceNotFoundException("Room with id {" + id + "} does not exist");
        roomService.deleteById(id);
        return  ResponseEntity.ok(null);
    }

    @GetMapping("/available")
    public ResponseEntity<List<Room>> findAvailableRoom() {
        List<Room> rooms=roomService.findAvailableRoom();
        return  ResponseEntity.ok(rooms);

    }


}
