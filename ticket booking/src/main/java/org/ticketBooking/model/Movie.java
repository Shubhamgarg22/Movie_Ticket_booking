package org.ticketBooking.model;

public class Movie {
    private final String id;
    private final String name;


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Movie(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
