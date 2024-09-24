/*Holds individual route coordinates and their associated info*/
package com.example.mapsappjava;

import java.io.Serializable;

public class Waypoint implements Serializable {
    private double lat;
    private double lon;
    private String info;

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
