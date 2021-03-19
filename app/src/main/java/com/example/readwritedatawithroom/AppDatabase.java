package com.example.readwritedatawithroom;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {User.class, Round.class}, version = 4, exportSchema = true)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract RoundDao roundDao();
}