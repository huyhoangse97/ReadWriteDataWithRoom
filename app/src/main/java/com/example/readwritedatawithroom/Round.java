package com.example.readwritedatawithroom;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "round")
public class Round {
    @PrimaryKey (autoGenerate = true)
    private int uid;

    private int modeId;

    private String modeName;

    private int roundId;

    private String roundName;

    private String status; //lock, 0star, 1star, 3star;

    public Round(){
        uid = 100001;
        modeName = "simple";
        modeId = 124231;
        roundName = "round_1";
        roundId = 243241;
        status = "0star";
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getUid() {
        return uid;
    }

    public int getModeId() {
        return modeId;
    }

    public void setModeId(int modeId) {
        this.modeId = modeId;
    }

    public String getModeName() {
        return modeName;
    }

    public void setModeName(String modeName) {
        this.modeName = modeName;
    }

    public int getRoundId() {
        return roundId;
    }

    public void setRoundId(int roundId) {
        this.roundId = roundId;
    }

    public String getRoundName() {
        return roundName;
    }

    public void setRoundName(String roundName) {
        this.roundName = roundName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
