package com.example.event_manager.activities.compositeStrategyFilter;

import com.example.event_manager.activities.EventMaker.Event;

import java.util.ArrayList;

public abstract class FilterStrategy {

    ArrayList<Event> filteredList = new ArrayList<Event>();

    public abstract ArrayList<Event> filter(ArrayList<Event> eventList);

}
