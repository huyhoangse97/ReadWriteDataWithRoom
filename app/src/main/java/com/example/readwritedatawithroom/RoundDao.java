package com.example.readwritedatawithroom;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface RoundDao {
    @Query("SELECT * FROM round")
    List<Round> getAll();

    @Query("SELECT * FROM round WHERE uid == :uid LIMIT 1")
    Round getRoundByUid(int uid);

    @Query("SELECT * FROM round WHERE modeName == :modeName")
    List<Round> getRoundsByModeName(String modeName);

    @Query("SELECT * FROM round WHERE modeId == :modeId  AND roundId == :roundId LIMIT 1")
    Round getRoundByModeAndRoundId(int modeId, int roundId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Round round);

    @Update
    void update(Round round);

    @Query("DELETE FROM round WHERE uid = :uid")
    void deleteByUid(int uid);

    @Query("DELETE FROM round")
    void deleteAll();
}
