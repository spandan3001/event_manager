package com.example.event_manager.activities.Database;

import com.example.event_manager.activities.EventMaker.Event;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Database {

    private Event event;
    private DatabaseReference databaseReference;
    private String uid;
    private String user;

    public DatabaseReference getDatabase(){
        return FirebaseDatabase.getInstance().getReference();
    }

    public void addEvent(Event event, DatabaseReference databaseReference){
        databaseReference.push().setValue(event);
    }

//    /**
//     * @param event
//     * @param databaseReference
//     * @param uid
//     * @param user
//     */
    public void pendingEvent(Event event, DatabaseReference databaseReference,String uid,String user){
        this.event = event;
        this.databaseReference = databaseReference;
        this.uid = uid;
        this.user = user;
        databaseReference.child("Pending Events").child(user).child(uid).push().setValue(event);
    }

}
