package org.ticketBooking.services;

import org.ticketBooking.exceptions.NotFoundException;
import org.ticketBooking.exceptions.ScreenAlreadyOccupiedException;
import org.ticketBooking.model.Movie;
import org.ticketBooking.model.Screen;
import org.ticketBooking.model.Show;

import java.util.*;

public class ShowService {
    private final Map<String, Show> shows;

    public ShowService(Map<String, Show> shows) {
        this.shows = shows;
    }

    public Show getShow(final String showId){
        if(showId==null){
            throw new  RuntimeException("id can't be empty");
        }
        if (!shows.containsKey(showId)) {
            throw new NotFoundException();
        }

        return shows.get(showId);
    }
    public Show createShow(final Movie movie , final Screen screen , final Date startTime , final Integer durationInMinutes){
        if(!checkIfShowCreationAllowed(screen , startTime , durationInMinutes)){
            throw new ScreenAlreadyOccupiedException();
        }
        String showId = UUID.randomUUID().toString();
        final Show show= new Show(showId , movie,screen ,startTime , durationInMinutes);
        shows.put(showId , show);
        return show;
    }
    private boolean checkIfShowCreationAllowed(final Screen screen, final Date startTime, final Integer durationInMinutes) {
        return true;
    }

    private List<Show> getShowsForScreen(final Screen screen){
        final List<Show> response = new ArrayList<>();
        for(Show show : shows.values()){
            if(show.getScreen().equals(screen)){
                response.add(show);
            }
        }
        return response;
    }


}
