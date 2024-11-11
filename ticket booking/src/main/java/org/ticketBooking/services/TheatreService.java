package org.ticketBooking.services;

import org.ticketBooking.exceptions.InvalidStateException;
import org.ticketBooking.exceptions.NotFoundException;
import org.ticketBooking.model.Screen;
import org.ticketBooking.model.Seat;
import org.ticketBooking.model.Theatre;

import java.util.Map;
import java.util.UUID;

public class TheatreService {
    private static  Map<String , Theatre> theatres;
    private final Map<String , Screen>screens;
    private final Map<String , Seat> seats;

    public TheatreService(Map<String, Theatre> theatres, Map<String, Screen> screens, Map<String, Seat> seats) {
        this.theatres = theatres;
        this.screens = screens;
        this.seats = seats;
    }

    public Theatre getTheatre( final String theatreId) {
        if (!theatres.containsKey(theatreId)) {
            throw new NotFoundException();
        }
        return theatres.get(theatreId);
    }
    public Screen getScreen( final String screenId){
        if(!screens.containsKey(screenId)){
            throw new NotFoundException();
        }
        return screens.get(screenId);
    }
    public Seat getSeat( final String seatId){
        if(!seats.containsKey(seatId)){
            throw new NotFoundException();
        }
        return seats.get(seatId);
    }
    public static Theatre createTheatre(final String theatreName) {
        if (theatreName == null) {
            throw new RuntimeException("Theatre name can't be empty") ;
        }

        String theatreId = UUID.randomUUID().toString();
        Theatre theatre = new Theatre(theatreId , theatreName);
        theatres.put(theatreId , theatre);
        return theatre;
    }
    public Screen createScreen(final String screenName , final Theatre theatre) {
        if (screenName == null || theatre==null) {
            throw new RuntimeException("Name can't be empty") ;
        }

        String screenId = UUID.randomUUID().toString();
        Screen screen = new Screen(screenId , screenName , theatre);
        screens.put(screenId , screen);
        return screen;
    }
    public Screen createScreenInTheatre(final String screenName , final Theatre theatre) {
        if (screenName == null || theatre==null) {
            throw new RuntimeException("Name can't be empty") ;
        }

        Screen screen = createScreen(screenName, theatre);
        theatre.addScreen(screen);
        return screen;
    }

    public Seat createSeatInScreen(final Integer rowNo , final Integer seatNo , final Screen screen){
        if(rowNo==null || seatNo==null||screen==null){
            throw new InvalidStateException();
        }
        String seatId = UUID.randomUUID().toString();
        Seat seat = new Seat(seatId , rowNo , seatNo);
        seats.put(seatId , seat);
        screen.addSeat(seat);
        return seat;
    }

}
