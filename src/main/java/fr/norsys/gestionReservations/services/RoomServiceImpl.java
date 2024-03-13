package fr.norsys.gestionReservations.services;

import fr.norsys.gestionReservations.entities.Room;
import fr.norsys.gestionReservations.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RoomServiceImpl implements RoomService{
    @Autowired
    private RoomRepository repository;
    @Override
    public List<Room> getAll() {
        return repository.findAll();
    }

    @Override
    public Room findById(int id) {
        Optional<Room> room=repository.findById(id);

        return room.orElse(null);
    }
    @Override
    public List<Room> findAvailableRoom() {
        return repository.findAvailableRoom(LocalDateTime.now());
    }

    @Override
    public Room save(Room room) {
        repository.save(room);

        return room;
    }

    @Override
    public Room updateRoom(int id, Room newRoom) {
        Room updatedRoom=repository.getById(id);
        updatedRoom.setRoomName(newRoom.getRoomName());
        updatedRoom.setCapacity(newRoom.getCapacity());
        return repository.save(updatedRoom);
    }

    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }


}
