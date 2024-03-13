package fr.norsys.gestionReservations.services;

import fr.norsys.gestionReservations.entities.Room;

import java.util.Date;
import java.util.List;

public interface RoomService {

    List<Room> getAll();
    Room findById(int id) ;

    Room save(Room room);

    Room updateRoom(int id, Room newRoom) ;

    void deleteById(int id);

    List<Room> findAvailableRoom();

}
