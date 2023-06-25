package com.example.event_manager.activities.compositeStrategyFilter;


import com.example.event_manager.activities.EventMaker.Event;

import java.util.ArrayList;

public class UniversityHoursFilter extends FilterStrategy {

    @Override
    public ArrayList<Event> filter(ArrayList<Event> eventList) {
        if(filteredList!=null){
            filteredList.clear();

        }
        // TODO Auto-generated method stub
        for(Event e:eventList){
            if(e.time.equals("University Hours")){
                filteredList.add(e);
            }
        }
        return filteredList;
    }
}
