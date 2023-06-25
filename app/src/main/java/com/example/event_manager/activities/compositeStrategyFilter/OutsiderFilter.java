package com.example.event_manager.activities.compositeStrategyFilter;



import com.example.event_manager.activities.EventMaker.Event;

import java.util.ArrayList;

public class OutsiderFilter extends FilterStrategy {

    @Override
    public ArrayList<Event> filter(ArrayList<Event> eventList) {
        if(filteredList!=null){
            filteredList.clear();

        }
        for(Event e:eventList){
            if(e.category.equals("Outsider")){
                filteredList.add(e);
            }
        }
        return filteredList;
    }

}