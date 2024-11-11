package org.ticketBooking.model;

import org.ticketBooking.exceptions.InvalidStateException;

import java.util.List;

public class Booking {
    private final String id;
    private final Show show;
    private final List<Seat> seatsBooked;
    private final String user;
    private BookingStatus bookingStatus;

    public Booking( final String id,  final Show show,  final String user, final List<Seat> seatsBooked) {
        if(id==null || show==null || user==null || seatsBooked==null){
            throw new RuntimeException("can't find");
        }
        this.id = id;
        this.show = show;
        this.seatsBooked = seatsBooked;
        this.user = user;
        this.bookingStatus = BookingStatus.Created;
    }

    public boolean isConfirmed() {
        return this.bookingStatus == BookingStatus.Confirmed;
    }

    public void confirmBooking() {
        if (this.bookingStatus != BookingStatus.Created) {
            throw new InvalidStateException();
        }
        this.bookingStatus = BookingStatus.Confirmed;
    }

    public void expireBooking() {
        if (this.bookingStatus != BookingStatus.Created) {
            throw new InvalidStateException();
        }
        this.bookingStatus = BookingStatus.Expired;
    }

    public String getId() {
        return id;
    }

    public Show getShow() {
        return show;
    }

    public List<Seat> getSeatsBooked() {
        return seatsBooked;
    }

    public String getUser() {
        return user;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }
}
