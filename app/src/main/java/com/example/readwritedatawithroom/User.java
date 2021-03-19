package com.example.readwritedatawithroom;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey (autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "first_name")
    public String firstName;

    @ColumnInfo(name = "last_name")
    public String lastName;

    public User(){
        firstName = "Huy Hoang";
        lastName = "Nguyen";
    }

    public User(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getName() {
        return (lastName + " " + firstName);
    }

    public int getUid() {
        return this.uid;
    }
}