package org.ticketBooking.services;

import org.ticketBooking.exceptions.BadRequestException;
import org.ticketBooking.exceptions.NotFoundException;
import org.ticketBooking.exceptions.SeatPermanentlyUnavailableException;
import org.ticketBooking.model.Booking;
import org.ticketBooking.model.Seat;
import org.ticketBooking.model.Show;
import org.ticketBooking.providers.SeatLockProvider;

import java.util.*;
import java.util.stream.Collectors;

public class BookingService {
    private final Map<String, Booking> showBookings;
    private final SeatLockProvider seatLockProvider;

    public BookingService(SeatLockProvider seatLockProvider) {
        this.seatLockProvider = seatLockProvider;
        this.showBookings = new HashMap<>();
    }

    public Booking getBooking( final String bookingId) {
        if (!showBookings.containsKey(bookingId)) {
            throw new NotFoundException();
        }
        return showBookings.get(bookingId);
    }

    public List<Booking> getAllBookings(final Show show) {
        List<Booking> response = new ArrayList<>();
        for (Booking booking : showBookings.values()) {
            if (booking.getShow().equals(show)) {
                response.add(booking);
            }
        }

        return response;
    }

    public Booking createBooking( final String userId,  final Show show, final List<Seat> seats) {
        if (isAnySeatAlreadyBooked(show, seats)) {
            throw new SeatPermanentlyUnavailableException();
        }
        seatLockProvider.lockSeats(show, seats, userId);
        final String bookingId = UUID.randomUUID().toString();
        final Booking newBooking = new Booking(bookingId, show, userId, seats);
        showBookings.put(bookingId, newBooking);
        return newBooking;
    }

    public List<Seat> getBookedSeats( final Show show) {
        return getAllBookings(show).stream()
                .filter(Booking::isConfirmed)
                .map(Booking::getSeatsBooked)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    public void confirmBooking( final Booking booking,  final String user) {
        if (!booking.getUser().equals(user)) {
            throw new BadRequestException();
        }

        for (Seat seat : booking.getSeatsBooked()) {
            if (!seatLockProvider.validateLock(booking.getShow(), seat, user)) {
                throw new BadRequestException();
            }
        }
        booking.confirmBooking();
    }

    private boolean isAnySeatAlreadyBooked(final Show show, final List<Seat> seats) {
        final List<Seat> bookedSeats = getBookedSeats(show);
        for (Seat seat : seats) {
            if (bookedSeats.contains(seat)) {
                return true;
            }
        }
        return false;
    }
}
