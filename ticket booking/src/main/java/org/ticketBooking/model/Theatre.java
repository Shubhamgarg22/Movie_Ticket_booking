package org.ticketBooking.model;

import java.util.ArrayList;
import java.util.List;

public class Theatre {
    private final String id;
    private final String name;
    private final List<Screen> screens;
    public Theatre( final String id,  final String name) {
        if(id==null || name==null){
            throw new RuntimeException();
        }
        this.id = id;
        this.name = name;
        this.screens = new ArrayList<>();
    }

    public void addScreen( final  Screen screen) {
        if(screen==null){
            throw new RuntimeException("screen can't be null");
        }
        screens.add(screen);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Screen> getScreens() {
        return screens;
    }
}
