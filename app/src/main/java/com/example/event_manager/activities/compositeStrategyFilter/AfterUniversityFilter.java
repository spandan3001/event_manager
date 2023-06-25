package com.example.event_manager.activities.compositeStrategyFilter;


import com.example.event_manager.activities.EventMaker.Event;

import java.util.ArrayList;

public class AfterUniversityFilter extends FilterStrategy {

    @Override
    public ArrayList<Event> filter(ArrayList<Event> eventList) {
        if(filteredList!=null){
            filteredList.clear();

        }
        for(Event e:eventList){
            if(e.time.equals("After University")){
                filteredList.add(e);
            }
        }
        return filteredList;
    }
}
