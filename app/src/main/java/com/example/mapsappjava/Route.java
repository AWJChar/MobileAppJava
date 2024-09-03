package com.example.mapsappjava;

public class Route {

    private String routeName;
    private double distance;
    //private x routeCoordinates;

    public Route(String routeName, double distance/*, x routeCoordinates*/) {
        this.routeName = routeName;
        this.distance = distance;
        //this.routeCoordinates = routeCoordinates;

    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    /*
    public void setRouteCoordinates(x routeCoordinates) {
        this.routeCoordinates = routeCoordinates;
    }
    */

    public double getDistance() {
        return distance;
    }

    public String getRouteName() {
        return routeName;
    }
}
