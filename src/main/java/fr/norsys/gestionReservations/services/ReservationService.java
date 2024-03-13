package fr.norsys.gestionReservations.services;

import fr.norsys.gestionReservations.entities.Reservation;
import fr.norsys.gestionReservations.entities.Room;

import java.util.Date;
import java.util.List;

public interface ReservationService {

    List<Reservation> getAll();
    Reservation findById(int id) ;

    Reservation save(Reservation reservation);

    Reservation updateReservation(int id, Reservation newReservation) ;


    void deleteById(int id);


}
