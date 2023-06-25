package com.example.event_manager.activities.TemplatePattern;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.event_manager.R;
import com.example.event_manager.activities.Database.Database;
import com.example.event_manager.activities.EventMaker.Event;
import com.example.event_manager.activities.NavigationDrawer.NavigationDrawer;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RequestedEventsActivity extends NavigationDrawer {

    ArrayList<Event> eventList;
    Database db;
    DatabaseReference myRef;
    RequestedEventsTemplate requestedEventsTemplate;
    String uid;
    String user;
    AppCompatActivity activity;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activity=this;
        mAuth = FirebaseAuth.getInstance();

        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame); //Remember this is the FrameLayout area within your activity_main.xml
        getLayoutInflater().inflate(R.layout.activity_requested_events, contentFrameLayout);

        db = new Database();
        myRef=db.getDatabase();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d("datasnapshot",""+dataSnapshot);

                for(DataSnapshot datas: dataSnapshot.getChildren()){

                    String child =datas.getKey();
                    Log.d("children",""+child);
                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child(child).child(uid);
                    ref.addValueEventListener(new ValueEventListener() {

                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if(dataSnapshot.hasChild("type")){
                                Log.d("typevalue",""+dataSnapshot.child("type").getValue().toString());
                                user=dataSnapshot.child("type").getValue().toString();
                                if(user.equals("admin")){
                                    requestedEventsTemplate = new AdminRequest();
                                }
                                else if(user.equals("student")){
                                    requestedEventsTemplate = new StudentRequest();
                                }
                                else if(user.equals("outsider")){
                                    requestedEventsTemplate = new OutsiderRequest();
                                }
                                requestedEventsTemplate.run(activity,uid);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser!=null){
            uid = currentUser.getUid();
            String email = currentUser.getEmail();
            String name = currentUser.getDisplayName();
            Log.d("UserFound"+uid+email+name,""+currentUser);
        }
        else{
            Log.d("NoUserFound",""+currentUser);
        }
    }
}

