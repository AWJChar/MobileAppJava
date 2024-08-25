package com.example.mapsappjava;

import static com.example.mapsappjava.NukeSSLCerts.nuke;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;


public class LoginActivity extends AppCompatActivity {

    private ProgressBar loadingPB;
    private TextView responseTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loadingPB = findViewById(R.id.idPBLoading);
        responseTV = findViewById(R.id.idTVResponse);
    }

    public void loginDetails(View view) {

        EditText usernameInput = findViewById(R.id.username_input);
        EditText passwordInput = findViewById(R.id.password_input);
        String username = usernameInput.getText().toString();
        String password = passwordInput.getText().toString();
        postDataUsingVolley(username,password);
    }

    private void postDataUsingVolley(String name, String job) {

        nuke();
        // on below line specifying the url at which we have to make a post request
        String url = "https://ec2-54-191-45-250.us-west-2.compute.amazonaws.com/login";
        // setting progress bar visibility on below line.
        loadingPB.setVisibility(View.VISIBLE);
        // creating a new variable for our request queue
        RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);

        JSONObject postData = new JSONObject();
        try {
            postData.put("email", name);
            postData.put("password", job);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        // making a string request on below line.
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, postData, new Response.Listener<JSONObject>() {


            @Override
            public void onResponse(JSONObject response) {
                // channing progress bar visibility on below line.
                loadingPB.setVisibility(View.GONE);
                // setting response to text view.
                responseTV.setText("Response from the API is :" + response);
                // displaying toast message.
                Toast.makeText(LoginActivity.this, "Data posted succesfully..", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // handling error on below line.
                loadingPB.setVisibility(View.GONE);
                responseTV.setText(error.getMessage());
                Toast.makeText(LoginActivity.this, "Fail to get response..", Toast.LENGTH_SHORT).show();
            }
        }) {

        };
        // adding request to queue to post the data.
        queue.add(request);
    }
}
