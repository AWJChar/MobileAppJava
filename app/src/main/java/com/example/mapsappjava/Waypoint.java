/*Holds individual waypoint data*/
package com.example.mapsappjava;

import java.io.Serializable;

public class Waypoint implements Serializable {
    private final double lat;
    private final double lon;
    private final String info;

    //Constructor for Waypoint class, info is left as 'null' by default
    public Waypoint(double lat, double lon, String info) {

        this.lat = lat;
        this.lon = lon;
        this.info = info;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public String getInfo() {
        return info;
    }
}
