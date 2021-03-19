package com.example.readwritedatawithroom;

import java.util.List;

public class RoundContainer {
    List<Round> rounds;

    public List<Round> getRounds() {
        return rounds;
    }

    public RoundContainer(List<Round> rounds){
        this.rounds = rounds;
    }
}
