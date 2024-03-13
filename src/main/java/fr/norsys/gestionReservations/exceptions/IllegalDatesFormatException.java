package fr.norsys.gestionReservations.exceptions;

public class IllegalDatesFormatException extends RuntimeException {
    public IllegalDatesFormatException(String s) {
        super(s);
    }
}
