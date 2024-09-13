package com.example.mapsappjava;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.maps.model.LatLng;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.Serializable;
import java.util.ArrayList;


public class Route extends AppCompatActivity  implements Serializable  {

    private String routeName;
    private String email;
    private ArrayList<Waypoint> coordinates = new ArrayList<>();
    private String coordPlaceHolder;

    public Route(String routeName, String Email, ArrayList<Waypoint> coordinates) {
        this.routeName = routeName;
        this.email = email;
        this.coordinates = coordinates;
    }
    public Route() {}

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public ArrayList<Waypoint> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(ArrayList<Waypoint> coordinates) {
        this.coordinates = coordinates;
    }

    public String getEmail() {
        return email;
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

    public void waypointsToCoordinates() throws JSONException {

        StringBuilder waypointSplitter = new StringBuilder();
        String lat;
        String lon;

        for (int i = 0; i < coordPlaceHolder.length(); i++) {

            if (coordPlaceHolder.charAt(i) != '}') {

                waypointSplitter.append(coordPlaceHolder.charAt(i));

            } else if (coordPlaceHolder.charAt(i) == '}') {

                waypointSplitter.append('}');
                JSONObject waypoints = new JSONObject(String.valueOf(waypointSplitter));
                lat = waypoints.getString("lat");
                lon = waypoints.getString("lon");
                Waypoint waypoint = new Waypoint(Double.parseDouble(lat), Double.parseDouble(lon));
                coordinates.add(waypoint);
                waypointSplitter.delete(0, waypointSplitter.length());
                i++;
                Log.d("coords1", coordinates.toString());
            }
        }
    }
}
