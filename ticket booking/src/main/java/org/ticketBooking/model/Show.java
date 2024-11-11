package org.ticketBooking.model;

import java.util.Date;

public class Show {
    private final String id;
    private final Movie movie;
    private final Screen screen;
    private final Date startTime;
    private final Integer durationInMinutes;

    public String getId() {
        return id;
    }

    public Movie getMovie() {
        return movie;
    }

    public Screen getScreen() {
        return screen;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Integer getDurationInSeconds() {
        return durationInMinutes;
    }

    public Show(String id, Movie movie, Screen screen, Date startTime, Integer durationInMinutes) {
        this.id = id;
        this.movie = movie;
        this.screen = screen;
        this.startTime = startTime;
        this.durationInMinutes = durationInMinutes;
    }
}
