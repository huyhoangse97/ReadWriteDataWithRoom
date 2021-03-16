package com.example.readwritedatawithroom;

import java.util.List;

public class ModeContainer {
    private List<Mode> modes;

    public List<Mode> getModes() {
        return modes;
    }

    public void setModes(List<Mode> modes) {
        this.modes = modes;
    }

    public int getSizeOfModes() {
        return modes.size();
    }

    public int getModesCount(int position) {
        return ((Mode)modes.get(position)).getSize();
    }

    public Object getMode(int position) {
        return modes.get(position);
    }

    public Object getChildrenByIndex(int groupIndex, int childIndex) {
        Mode group = modes.get(groupIndex);
        return group.getChildrenByIndex(childIndex);
    }

    public String getModeName(int groupPosition) {
        Mode mode = modes.get(groupPosition);
        return mode.getName();
    }
}
