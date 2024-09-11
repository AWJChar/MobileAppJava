package com.example.mapsappjava;

import java.io.Serializable;

public class Waypoint implements Serializable {
    private double lat;
    private double lon;

    public Waypoint(double lat, double lon) {

        this.lat = lat;
        this.lon = lon;
    }


    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    @Override
    public String toString() {
        return "Waypoint{" +
                "lat=" + lat +
                ", lon=" + lon +
                '}';
    }

}
