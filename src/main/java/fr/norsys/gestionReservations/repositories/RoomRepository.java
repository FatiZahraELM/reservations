package fr.norsys.gestionReservations.repositories;

import fr.norsys.gestionReservations.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room,Integer> {
    @Query(nativeQuery =true,value="select * from rooms r where r.room_id NOT IN (select room_id from Reserations where due_date>:date_reservation ) ")
    List<Room> findAvailableRoom(@Param("date_reservation") LocalDateTime date_reservation);

}
