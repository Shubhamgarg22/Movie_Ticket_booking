package org.ticketBooking.Controllers;

import org.ticketBooking.model.Movie;
import org.ticketBooking.model.Screen;
import org.ticketBooking.model.Seat;
import org.ticketBooking.model.Show;
import org.ticketBooking.services.MovieService;
import org.ticketBooking.services.SeatAvailabilityService;
import org.ticketBooking.services.ShowService;
import org.ticketBooking.services.TheatreService;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ShowController {
    private final SeatAvailabilityService seatAvailabilityService;
    private final ShowService showService;
    private final TheatreService theatreService;
    private final MovieService movieService;

    public ShowController(SeatAvailabilityService seatAvailabilityService, ShowService showService, TheatreService theatreService, MovieService movieService) {
        this.seatAvailabilityService = seatAvailabilityService;
        this.showService = showService;
        this.theatreService = theatreService;
        this.movieService = movieService;
    }
    public String createShow( final String movieId,  final String screenId,  final Date startTime, final Integer durationInMinutes){
        final Screen screen = theatreService.getScreen(screenId);
        final Movie movie = movieService.getMovie(movieId);
        return showService.createShow(movie, screen, startTime, durationInMinutes).getId();
    }
    public List<String> getAvailableSeats( final String showId) {
        final Show show = showService.getShow(showId);
        final List<Seat> availableSeats = seatAvailabilityService.getAvailableSeats(show);
        return availableSeats.stream().map(Seat::getId).collect(Collectors.toList());
    }
}
