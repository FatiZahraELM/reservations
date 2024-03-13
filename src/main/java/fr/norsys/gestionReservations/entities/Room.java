package fr.norsys.gestionReservations.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name="rooms")
@Data
public class Room {
    @Id
    @Column(name = "room_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roomId;

    @Column(name="room_name")
    private String roomName;

    @Column(name="capacity")
    private int capacity;

    @OneToMany(mappedBy = "room", cascade =CascadeType.ALL)
    @JsonIgnore
    private List<Reservation> reservations;

    public Room(String roomName, int capacity,String availability) {
        this.roomName = roomName;
        this.capacity=capacity;
    }

    public Room() {
    }

}
