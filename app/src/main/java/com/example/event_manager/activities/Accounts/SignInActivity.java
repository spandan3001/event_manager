package com.example.event_manager.activities.Accounts;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.example.event_manager.activities.EventBoard.EventBoardActivity;
import com.example.event_manager.activities.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;


public class SignInActivity extends AppCompatActivity {

    TextView mEmailView,mSignUpView;
    TextView mPasswordView;

    String password;
    String email;

    private FirebaseAuth mAuth;
    DatabaseReference myRef;
    DatabaseReference studRef;
    DatabaseReference adminRef;
    DatabaseReference outRef;


    Intent intent;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        mEmailView = findViewById(R.id.email);
        mPasswordView = findViewById(R.id.password);
        mSignUpView=findViewById(R.id.link_signup);

        intent = new Intent(this, EventBoardActivity.class);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        studRef = myRef.child("Student");
        adminRef= myRef.child("Admin");
        outRef = myRef.child("Outsider");

        mSignUpView.setOnClickListener(view -> {
            // Start the Signup activity
            Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
            startActivity(intent);
        });
    }

    public void buttonOnClick(View view) {

        if (view.getId() == R.id.email_sign_in_button) {
            email = mEmailView.getText().toString();
            password = mPasswordView.getText().toString();

            if (TextUtils.isEmpty(email)) {
                Toast.makeText(this, "Please enter your email.", Toast.LENGTH_SHORT).show();
                return;
            }

            if (TextUtils.isEmpty(password)) {
                Toast.makeText(this, "Please enter your password.", Toast.LENGTH_SHORT).show();
                return;
            }

            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
//                                    Log.d(TAG, "signInWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
//                                    if (user!=null) {
//                                        finish();
//                                        startActivity(i);
//                                    }
                                startActivity(intent);

//                                    updateUI(user);
                            } else {
                                // If sign in fails, display a message to the user.
//                                    Log.w(TAG, "signInWithEmail:failure", task.getException());

                                Toast.makeText(SignInActivity.this, "Authentication failed." + Objects.requireNonNull(task.getException()).getMessage(),
                                        Toast.LENGTH_SHORT).show();
//                                    updateUI(null);
                            }

                            // ...
                        }
                    });
        }
    }

}
