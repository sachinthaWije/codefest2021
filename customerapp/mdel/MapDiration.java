package com.lec.customerapp.mdel;

public class MapDiration {
    String textDuration;
    int minDuration;

    public MapDiration() {
    }


    public MapDiration(String textDuration) {
        this.textDuration = textDuration;
    }

    public MapDiration(String textDuration, int minDuration) {
        this.textDuration = textDuration;
        this.minDuration = minDuration;
    }

    public String getTextDuration() {
        return textDuration;
    }

    public void setTextDuration(String textTime) {
        this.textDuration = textTime;
    }

    public int getMinDuration() {
        return minDuration;
    }

    public void setMinDuration(int minDuration) {
        this.minDuration = minDuration;
    }
}
