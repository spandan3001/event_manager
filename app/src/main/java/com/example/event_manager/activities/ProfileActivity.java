package com.example.event_manager.activities;


import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.event_manager.activities.NavigationDrawer.NavigationDrawer;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.example.event_manager.activities.Database.Database;

import java.util.Objects;

public class ProfileActivity extends NavigationDrawer {

    ImageView img ;
    TextView name,email,phoneNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_profile);

        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame); //Remember this is the FrameLayout area within your activity_main.xml
        getLayoutInflater().inflate(R.layout.activity_profile, contentFrameLayout);


        img = findViewById(R.id.profile_image);
        name = findViewById(R.id.name_textview);
        email = findViewById(R.id.email_textview);
        phoneNo = findViewById(R.id.phone_textview);



        Database database = new Database();

        DatabaseReference mDatabase = database.getDatabase();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        String userId = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();


        mDatabase.child("Student").child(userId).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DataSnapshot dataSnapshot = task.getResult();

                if (dataSnapshot.exists()) {
                    name.setText(String.valueOf(dataSnapshot.child("name").getValue()));
                    email.setText(String.valueOf(dataSnapshot.child("email").getValue()));
                    phoneNo.setText(String.valueOf(dataSnapshot.child("ID").getValue()));
                }
            }
        });

        mDatabase.child("Admin").child(userId).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DataSnapshot dataSnapshot = task.getResult();

                if (dataSnapshot.exists()) {

                    name.setText(String.valueOf(dataSnapshot.child("name").getValue()));
                    email.setText(String.valueOf(dataSnapshot.child("email").getValue()));
                    phoneNo.setText(String.valueOf(dataSnapshot.child("ID").getValue()));

                }
            }
        });
        mDatabase.child("Outsider").child(userId).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DataSnapshot dataSnapshot = task.getResult();

                if (dataSnapshot.exists()) {
                    name.setText(String.valueOf(dataSnapshot.child("name").getValue()));
                    email.setText(String.valueOf(dataSnapshot.child("email").getValue()));
                    //phoneNo.setText(String.valueOf(dataSnapshot.child("ID").getValue()));
                }
            }
        });


    }
}