package model.entities;

import model.exceptions.DomainException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Reservation {

    private Integer roomNumber;
    private Date checkIn;
    private Date checkOut;

    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public Reservation() {}

    public Reservation(Integer roomNumber, Date checkIn, Date checkOut) throws DomainException {
        // Declarando dentro do construtor - programação defensiva - considerada uma boa prática
        if (!checkOut.after(checkIn)) {
            throw new DomainException("Check-Out date must be after Check-In date");
        }

        this.roomNumber = roomNumber;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public long duration() {
        long diff = checkOut.getTime() - checkIn.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    public String updateDates(Date checkIn, Date checkOut) throws DomainException {

        Date now = new Date();
        if (checkIn.before(now) || checkOut.after(now)){
            throw new DomainException("Updates in reservation dates must be future dates");
        }
        if (!checkOut.after(checkIn)) {
            throw new DomainException("Check-Out date must be after Check-In date");
        }

        this.checkIn = checkIn;
        this.checkOut = checkOut;

        return null;
    }

    public String toString() {
        return "Room "
                + roomNumber
                + ", check-in: "
                + checkIn
                + sdf.format(checkIn)
                + ", check-out: "
                + sdf.format(checkOut)
                + ", "
                + duration()
                + " nights";
    }

}
