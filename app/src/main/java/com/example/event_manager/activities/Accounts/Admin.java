package com.example.event_manager.activities.Accounts;

import com.google.firebase.database.DatabaseReference;

public class Admin implements User {
    public String ID, name, key,email,password;

    public Admin(String key, String ID, String name,String email,String password) {
        this.ID = ID;
        this.key = key;
        this.name = name;
        this.email=email;
        this.password=password;
    }

    @Override
    public void addToDB(DatabaseReference adminRef) {
        System.out.println(this.key);
        adminRef.child(this.key).child("ID").setValue(this.ID);
        adminRef.child(this.key).child("name").setValue(this.name);
        adminRef.child(this.key).child("email").setValue(this.email);
        adminRef.child(this.key).child("password").setValue(this.password);
        adminRef.child(this.key).child("type").setValue("admin");
    }
}
