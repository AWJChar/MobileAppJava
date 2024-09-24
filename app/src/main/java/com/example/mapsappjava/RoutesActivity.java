/*Controls routes activity where users select the route they would like to follow*/
package com.example.mapsappjava;

import static com.example.mapsappjava.NukeSSLCerts.nuke;
import android.content.Intent;
import android.os.Bundle;
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

    //Switches to routes activity view
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routes);
    }

    //Listens for button click and sends button ID to getRouteDetails method
    public void buttonClick(View view) {
        Button button = (Button) view;
        String buttonText = button.getText().toString();
        Log.d("buttonclick", buttonText);
        getRouteDetails(buttonText);
    }

    //Gets route details from server and sends them to MapsActivity
    public void getRouteDetails(String routeName) {

        nuke();

        RequestQueue queue = Volley.newRequestQueue(this);

        //Appends route name to URL
        String routeNameAppend = ("?routeName=" + routeName);
        String url = "https://ec2-13-60-13-72.eu-north-1.compute.amazonaws.com/routes" + routeNameAppend;

        //Creates get request for route details
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            //Creates route object with route details JSON obj and sends it to MapsActivity in intent
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
