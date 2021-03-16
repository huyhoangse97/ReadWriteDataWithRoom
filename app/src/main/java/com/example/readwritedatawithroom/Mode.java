package com.example.readwritedatawithroom;

import java.util.List;

@Entity
public class Mode {
    private String name;
    private List<Round> rounds;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Round> getRounds() {
        return rounds;
    }

    public void setRounds(List<Round> rounds) {
        this.rounds = rounds;
    }

    public int getSize() {
        return rounds.size();
    }

    public Object getChildrenByIndex(int childIndex) {
        return rounds.get(childIndex);
    }
}
