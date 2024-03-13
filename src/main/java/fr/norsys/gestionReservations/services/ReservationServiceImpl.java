package fr.norsys.gestionReservations.services;

import fr.norsys.gestionReservations.entities.Reservation;
import fr.norsys.gestionReservations.entities.Room;
import fr.norsys.gestionReservations.exceptions.AlreadyUsedException;
import fr.norsys.gestionReservations.exceptions.IllegalDatesFormatException;
import fr.norsys.gestionReservations.repositories.ReservationRepository;
import fr.norsys.gestionReservations.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService{
    @Autowired
    private ReservationRepository repository;
    @Override
    public List<Reservation> getAll() {
        return repository.findAll();
    }

    @Override
    public Reservation findById(int id) {
        Optional<Reservation> reservation=repository.findById(id);
        return reservation.orElse(null) ;
    }

    @Override
    public Reservation save(Reservation reservation) {
       repository.save(reservation);
        return reservation;
    }

    @Override
    public Reservation updateReservation(int id, Reservation newReservation) {
        Reservation updatedReservation=repository.getById(id);
        updatedReservation.setDateReservation(newReservation.getDateReservation());
        updatedReservation.setRoom(newReservation.getRoom());
        updatedReservation.setDueDate(newReservation.getDueDate());

        return repository.save(updatedReservation);
    }

    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }




}
