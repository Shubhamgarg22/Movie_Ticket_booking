package org.ticketBooking.Controllers;

import org.ticketBooking.services.MovieService;

public class MovieController {
    final private MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    public String createMovie( final String movieName) {
        return movieService.createMovie(movieName).getId();
    }
}
