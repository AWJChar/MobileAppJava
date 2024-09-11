package com.example.mapsappjava;

import static com.example.mapsappjava.NukeSSLCerts.nuke;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;


import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class RoutesActivity extends AppCompatActivity {

    private Route route;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routes);
    }

    public void makeRoute(View view) {

        getRouteDetails("Home_Route");
        Log.d("Route", "makeRoute: works");

    }

    public void getRouteDetails(String routeName) {

        nuke();

        RequestQueue queue = Volley.newRequestQueue(this);

        String routeNameAppend = ("?routeName=" + routeName);

        String url = "https://ec2-54-191-45-250.us-west-2.compute.amazonaws.com/routes" + routeNameAppend;

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    Route route = new Route();
                    route.setRouteName(object.getString("name"));
                    route.setCoordPlaceHolder(object.getString("waypoints"));
                    route.setEmail(object.getString("email"));
                    route.waypointsToCoordinates();
                    Log.d("Route", response);
                    Intent intent = new Intent(RoutesActivity.this, MapsActivity.class);
                    intent.putExtra("route", route);
                    startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error", error.toString());
            }
        });
        queue.add(request);
    }
}
