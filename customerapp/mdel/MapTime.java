package com.lec.customerapp.mdel;

public class MapTime {
    String textTime;
    int minTime;

    public MapTime() {
    }


    public MapTime(String textTime) {
        this.textTime = textTime;
    }

    public MapTime(String textTime, int minTime) {
        this.textTime = textTime;
        this.minTime = minTime;
    }

    public String getTextTime() {
        return textTime;
    }

    public void setTextTime(String textTime) {
        this.textTime = textTime;
    }

    public int getMinTime() {
        return minTime;
    }

    public void setMinTime(int minTime) {
        this.minTime = minTime;
    }
}
