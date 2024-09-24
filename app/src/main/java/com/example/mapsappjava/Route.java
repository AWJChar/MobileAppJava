/*Stores route data and converts JSON array data to waypoint objects which store LatLng and info*/
package com.example.mapsappjava;

import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.Serializable;
import java.util.ArrayList;

public class Route extends AppCompatActivity  implements Serializable  {

    private String routeName;
    private String email;
    private ArrayList<Waypoint> coordinates = new ArrayList<>();
    private String coordPlaceHolder;

    //Constructor for Route class
    public Route() {}

    //Setters and Getters
    public void setEmail(String email) {
        this.email = email;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public ArrayList<Waypoint> getCoordinates() {
        return coordinates;
    }

    public void setCoordPlaceHolder(String coordPlaceHolder) {
        this.coordPlaceHolder = coordPlaceHolder;
    }

    //Takes array of JSON objects returned from server and creates Waypoint objects
    // holding individual points and their data
    public void waypointsToCoordinates() throws JSONException {

        StringBuilder waypointSplitter = new StringBuilder();
        String lat;
        String lon;
        String info;
        Log.d("Waypoints", coordPlaceHolder);

        //loops through route coordinates and creates Waypoint objects as they are separated
        for (int i = 0; i < coordPlaceHolder.length(); i++) {

            if (coordPlaceHolder.charAt(i) != '}') {

                waypointSplitter.append(coordPlaceHolder.charAt(i));

            } else if (coordPlaceHolder.charAt(i) == '}') {

                waypointSplitter.append('}');
                JSONObject waypoints = new JSONObject(String.valueOf(waypointSplitter));
                lat = waypoints.getString("lat");
                lon = waypoints.getString("lon");
                if (!waypoints.getString("info").isBlank()) {
                    info = waypoints.getString("info");
                    Log.d("Waypoints", info);
                }else {
                    info = "null";
                }

                //Adds Waypoint object to arraylist which is used in MapsActivity to create the route
                Waypoint waypoint = new Waypoint(Double.parseDouble(lat), Double.parseDouble(lon), info);
                coordinates.add(waypoint);
                waypointSplitter.delete(0, waypointSplitter.length());
                i++;

            }
        }
    }
}
