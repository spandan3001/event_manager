package com.example.event_manager.activities.TemplatePattern;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.event_manager.activities.R;
import com.example.event_manager.activities.Database.Database;
import com.example.event_manager.activities.EventBoard.AndroidDataAdapter;
import com.example.event_manager.activities.EventBoard.EventBoardSingleton;
import com.example.event_manager.activities.EventBoard.RecyclerItemClickListener;
import com.example.event_manager.activities.EventMaker.Event;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

public class StudentRequest extends RequestedEventsTemplate {

    @Override
    public void getViews() {
        pendingText = (TextView) activity.findViewById(R.id.Pending);
        pendingCount  = (TextView) activity.findViewById(R.id.pendingCount);

        approvedText = (TextView) activity.findViewById(R.id.Approved);
        approvedCount  = (TextView) activity.findViewById(R.id.approvedCount);

        disapprovedText = (TextView) activity.findViewById(R.id.DisApproved);
        disapprovedCount  = (TextView) activity.findViewById(R.id.disApprovedCount);

        pendingRecycler =(RecyclerView) activity.findViewById(R.id.recycler_pending);
        approvedRecycler =(RecyclerView) activity.findViewById(R.id.recycler_approved);
        disapprovedRecycler =(RecyclerView) activity.findViewById(R.id.recycler_disapproved);

        addButton = (FloatingActionButton) activity.findViewById(R.id.add);
        deleteButton = (FloatingActionButton) activity.findViewById(R.id.delete);
        linearLayout =(LinearLayout) activity.findViewById(R.id.Controls);
        linearLayout.setVisibility(View.VISIBLE);


        pendingRecycler.addOnItemTouchListener(
                new RecyclerItemClickListener(activity, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int i) {

//                        Toast.makeText(view.getContext(), "position= " + i, Toast.LENGTH_LONG).show();
                       AndroidDataAdapter.position=i;
                       AndroidDataAdapter.id="Pending";
                    }
                })
        );

        approvedRecycler.addOnItemTouchListener(
                new RecyclerItemClickListener(activity, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int i) {

//                        Toast.makeText(view.getContext(), "position= " + i, Toast.LENGTH_LONG).show();
                        AndroidDataAdapter.position=i;
                        AndroidDataAdapter.id="Approved";
                    }
                })
        );

        disapprovedRecycler.addOnItemTouchListener(
                new RecyclerItemClickListener(activity, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int i) {

//                        Toast.makeText(view.getContext(), "position= " + i, Toast.LENGTH_LONG).show();
                        AndroidDataAdapter.position=i;
                        AndroidDataAdapter.id="Disapproved";
                    }
                })
        );
    }

    @Override
    public void showpending(String uid) {
        System.out.println("showpendingvaries:Student's own pending events displayed.");
        pendingText.setVisibility(View.VISIBLE);
        pendingCount.setVisibility(View.VISIBLE);
        pendingRecycler.setVisibility(View.VISIBLE);
        //filter list by pending of students;
        if(pendingList!=null){
            pendingList.clear();
        }
        Database db = new Database();
        DatabaseReference mDatabase = db.getDatabase();
        mDatabase.child("Pending Events").child("student").child(uid).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Event event = dataSnapshot.getValue(Event.class);
                Log.d("1", "getlist:" + event.name+event.category+event.venue+event.image);
                pendingList.add(event);
                EventBoardSingleton pendingBoard=EventBoardSingleton.getInstance();
                pendingBoard.initEventPanels(activity,pendingRecycler,pendingList);
                pendingCount.setText(pendingList.size()+" Results");
//                Log.d("1", "getlist:" + dataSnapshot);

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {


            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void showDeleteButton(String id) {
        uid=id;
        System.out.println("showDeleteButtonvaries: Remove pending event from database.");
        deleteButton.show();
        //change its onclick
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Database db = new Database();
                final DatabaseReference mDatabase = db.getDatabase();
                if(AndroidDataAdapter.id.equals("Approved") || AndroidDataAdapter.id.equals("Disapproved") ){


                    mDatabase.child(AndroidDataAdapter.id+" Events").child(uid).addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                            Event event=dataSnapshot.getValue(Event.class);
                            if(event.name.equals(AndroidDataAdapter.name)){
                                mDatabase.child(AndroidDataAdapter.id+" Events").child(uid).child(dataSnapshot.getKey()).removeValue();
                            }
                        }

                        @Override
                        public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                        }

                        @Override
                        public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                        }

                        @Override
                        public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }else if(AndroidDataAdapter.id.equals("Pending")){
                    mDatabase.child(AndroidDataAdapter.id+" Events").child("student").child(uid).addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                            Event event=dataSnapshot.getValue(Event.class);
                            if(event.name.equals(AndroidDataAdapter.name)){
                                mDatabase.child(AndroidDataAdapter.id+" Events").child("student").child(uid).child(dataSnapshot.getKey()).removeValue();
                            }
                        }

                        @Override
                        public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                        }

                        @Override
                        public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                        }

                        @Override
                        public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            }
        });
    }

    //Hook Methods
    @Override
    public boolean disablePost() { //override in student, outsider
        return false;
    }

}