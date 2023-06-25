package com.example.event_manager.activities.compositeStrategyFilter;


import com.example.event_manager.activities.EventMaker.Event;

import java.util.ArrayList;

public class FilterButton {
    private FilterStrategy filter;
    private  ArrayList<Event> filteredList;

    public FilterButton(FilterStrategy filter){
        this.filter = filter;
    }

    public ArrayList<Event> executeStrategy(ArrayList<Event> eventList){
        if(filteredList!=null){
            filteredList.clear();

        }
        filteredList=filter.filter(eventList);
        return filteredList;
    }
}