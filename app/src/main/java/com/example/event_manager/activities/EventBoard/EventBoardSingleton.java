package com.example.event_manager.activities.EventBoard;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.event_manager.activities.EventMaker.Event;

import java.util.ArrayList;

public class EventBoardSingleton extends AppCompatActivity {

    private static EventBoardSingleton instance;

    private ArrayList<Event> eventList=new ArrayList<>();

    private EventBoardSingleton(){}

    public static EventBoardSingleton getInstance(){
        if(instance==null){
            instance=new EventBoardSingleton();
        }
        return instance;
    }

    public ArrayList<Event> getEventList(){

        return eventList;
    }

    public void initEventPanels(Context context, RecyclerView mRecyclerView, ArrayList<Event> eventList) {
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context, 2);
        mRecyclerView.setLayoutManager(mLayoutManager);

        AndroidDataAdapter mAdapter = new AndroidDataAdapter(context, eventList);
        mRecyclerView.setAdapter(mAdapter);
    }

}

