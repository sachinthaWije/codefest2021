package com.lec.rider.model;

public class Event {
    String name;
    double lat,log;

    public Event() {
    }

    public Event(String name, double lat, double log) {
        this.name = name;
        this.lat = lat;
        this.log = log;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLog() {
        return log;
    }

    public void setLog(double log) {
        this.log = log;
    }
}
