package com.example.readwritedatawithroom.in_out;

public class RoundSelected {
    private int uid;

    private int modeId;

    private String modeName;

    private int roundId;

    private String roundName;

    private String status; //lock, 0star, 1star, 3star;

    public RoundSelected(){
        uid = 1;
        modeName = "simple";
        modeId = 1;
        roundName = "round_1";
        roundId = 1;
        status = "0star";
    }

    public RoundSelected(int uid, String modeName, int modeId, String roundName, int roundId, String status){
        this.uid = uid;
        this.modeName = modeName;
        this.modeId = modeId;
        this.roundName = roundName;
        this.roundId = roundId;
        this.status = status;
    }
}
