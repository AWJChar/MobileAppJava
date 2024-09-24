package com.example.mapsappjava;

import static com.example.mapsappjava.NukeSSLCerts.nuke;

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
        getUserDetails(username,password);
    }

    private void getUserDetails(String email, String password) {

        nuke();

        String detailsAppend = ("?email=" + email.toLowerCase() + "&password=" + password);

        String url = "https://ec2-13-60-13-72.eu-north-1.compute.amazonaws.com/login" + detailsAppend;

        loadingPB.setVisibility(View.VISIBLE);

        RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);


        // making a string request on below line.
        StringRequest request = new StringRequest(Request.Method.GET, url,  new Response.Listener<String>() {


            @Override
            public void onResponse(String response) {

                loadingPB.setVisibility(View.GONE);
                responseTV.setText(getString(R.string.response_API) + response);

                Toast.makeText(LoginActivity.this, "Data posted successfully..", Toast.LENGTH_SHORT).show();
                Log.d("Response", response.toString());

                try {
                    JSONObject object = new JSONObject(response);
                    Account loggedInAccount = new Account(object.getString("first_name"),
                            object.getString("surname"), object.getString("email"));

                    Log.d("Account", loggedInAccount.accountToString());
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
