/*Handles user logins and checks them against those stored in database, successful logins store the user in Accounts Class*/
package com.example.mapsappjava;

import static com.example.mapsappjava.NukeSSLCerts.nuke;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONException;
import org.json.JSONObject;


public class LoginActivity extends AppCompatActivity {

    private ProgressBar loadingPB;
    private TextView responseTV;

    //Switches view to login view
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loadingPB = findViewById(R.id.idPBLoading);
        responseTV = findViewById(R.id.idTVResponse);
    }

    //Takes login details from text and password fields and checks them against those stored in database
    public void loginDetails(View view) {

        EditText usernameInput = findViewById(R.id.username_input);
        EditText passwordInput = findViewById(R.id.password_input);
        String username = usernameInput.getText().toString();
        String password = passwordInput.getText().toString();
        getUserDetails(username,password);
    }

    //Takes login details from text and password fields and checks them against those stored in database
    private void getUserDetails(String email, String password) {

        nuke();

        //adds login details to server get request
        String detailsAppend = ("?email=" + email.toLowerCase() + "&password=" + password);
        String url = "https://ec2-13-60-13-72.eu-north-1.compute.amazonaws.com/login" + detailsAppend;

        //Sets loading icon to visible while server is contacted
        loadingPB.setVisibility(View.VISIBLE);

        //Creates new request queue
        RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);

        //Sends request to server
        StringRequest request = new StringRequest(Request.Method.GET, url,  new Response.Listener<String>() {


            @Override
            public void onResponse(String response) {

                //Hides loading icon
                loadingPB.setVisibility(View.GONE);

                Log.d("Response", response.toString());

                //Takes returned account details and creates JSON object, then creates new account object using retrieved data
                try {
                    JSONObject object = new JSONObject(response);
                    Account loggedInAccount = new Account(object.getString("first_name"),
                            object.getString("surname"), object.getString("email"));

                    Log.d("Account", loggedInAccount.accountToString());

                    //Switches user to routes activity on successful login
                    Intent intent = new Intent(LoginActivity.this, RoutesActivity.class);
                    startActivity(intent);

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }


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
