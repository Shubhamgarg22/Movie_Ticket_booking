package org.ticketBooking.Controllers;

import org.ticketBooking.exceptions.InvalidStateException;
import org.ticketBooking.model.Screen;
import org.ticketBooking.model.Theatre;
import org.ticketBooking.services.TheatreService;

public class TheatreController {
    final private TheatreService theatreService;

    public TheatreController(TheatreService theatreService) {
        this.theatreService = theatreService;
    }

    public String createTheater(final String theaterName){
        if(theaterName==null){
            throw new RuntimeException("Name can't be empty");
        }
        return TheatreService.createTheatre(theaterName).getId();
    }
    public String createScreenInTheathre(final String screenName , final String theatreId){
        final Theatre theatre = theatreService.getTheatre(theatreId);
        return theatreService.createScreen(screenName , theatre).getId();
    }
    public String createSeatInScreen(final Integer rowNo , final Integer seatNo , final String screenId){
        final Screen screen = theatreService.getScreen(screenId);
        return theatreService.createSeatInScreen(rowNo , seatNo , screen).getId();
    }

}
