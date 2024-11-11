package org.ticketBooking.Controllers;

import org.ticketBooking.model.Seat;
import org.ticketBooking.model.Show;
import org.ticketBooking.services.BookingService;
import org.ticketBooking.services.ShowService;
import org.ticketBooking.services.TheatreService;

import java.util.List;
import java.util.stream.Collectors;

public class BookingController {
    private final ShowService showService;
    private final BookingService bookingService;
    private final TheatreService theatreService;

    public BookingController(ShowService showService, BookingService bookingService, TheatreService theatreService) {
        this.showService = showService;
        this.bookingService = bookingService;
        this.theatreService = theatreService;
    }
    public String createBooking( final String userId,  final String showId, final List<String> seatsIds) {
        final Show show = showService.getShow(showId);
        final List<Seat> seats = seatsIds.stream().map(theatreService::getSeat).collect(Collectors.toList());
        return bookingService.createBooking(userId, show, seats).getId();
    }
}
