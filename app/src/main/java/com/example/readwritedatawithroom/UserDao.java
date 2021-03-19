package com.example.readwritedatawithroom;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface UserDao {
    @Query("SELECT * FROM USER")
    List<User> getAll();

    @Query("SELECT * FROM USER WHERE uid IN (:USERIds)")
    List<User> loadAllByIds(int[] USERIds);

    @Query("SELECT * FROM USER WHERE uid = :USERId LIMIT 1")
    User loadUserById(int USERId);

    @Query("SELECT * FROM USER WHERE first_name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    User findByName(String first, String last);

    @Insert(onConflict = REPLACE)
    void insertAll(User... USERs);

    @Delete
    void delete(User USER);

    @Query("DELETE FROM USER")
    void reset();
}