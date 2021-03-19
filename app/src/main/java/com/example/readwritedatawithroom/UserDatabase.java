package com.example.readwritedatawithroom;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {User.class}, version = 1, exportSchema = true)
public abstract class UserDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}