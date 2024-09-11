package com.example.mapsappjava;

import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.maps.model.LatLng;
import java.util.ArrayList;

public class Route extends AppCompatActivity {

    private String routeName;
    private double distance;
    ArrayList<LatLng> coordinates = new ArrayList<>();
    private String coordPlaceHolder;

    public Route(String routeName, double distance, ArrayList<LatLng> coordinates) {
        this.routeName = routeName;
        this.distance = distance;
        this.coordinates = coordinates;
    }
    public Route() {}

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public ArrayList<LatLng> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(ArrayList<LatLng> coordinates) {
        this.coordinates = coordinates;
    }

    public double getDistance() {
        return distance;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setCoordPlaceHolder(String coordPlaceHolder) {
        this.coordPlaceHolder = coordPlaceHolder;
    }

    public String getCoordPlaceHolder() {
        return coordPlaceHolder;
    }


}
