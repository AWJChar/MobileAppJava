package com.example.mapsappjava;

import static com.example.mapsappjava.NukeSSLCerts.nuke;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class CreateAccountActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
    }

    public void getDetails(View view) {
        Log.d("Account", "Button Works");
        EditText firstNameInput = findViewById(R.id.firstNameInput);
        EditText secondNameInput = findViewById(R.id.secondNameInput);
        EditText emailInput = findViewById(R.id.emailInput);
        EditText passwordInput1 = findViewById(R.id.passwordInput1);
        EditText passwordInput2 = findViewById(R.id.passwordinput2);

        if(passwordInput1.getText().toString().equals(passwordInput2.getText().toString())
                && emailInput.getText().toString().contains("@")
                && emailInput.getText().toString().contains(".")
                && !firstNameInput.getText().toString().isEmpty()
                && !secondNameInput.getText().toString().isEmpty()) {

            Account newAccount = new Account(firstNameInput.getText().toString(),
                    secondNameInput.getText().toString(), emailInput.getText().toString()
                    , passwordInput1.getText().toString());
            createAccount(newAccount);
        }
    }

    private void createAccount(Account newAccount) {

        nuke();

        String url = "https://ec2-13-60-13-72.eu-north-1.compute.amazonaws.com/create_user";

        RequestQueue queue = Volley.newRequestQueue(this);

        JSONObject postData = new JSONObject();
        try {
            postData.put("email", newAccount.getEmail());
            postData.put("first_name", newAccount.getFirstName());
            postData.put("surname", newAccount.getSecondName());
            postData.put("password", newAccount.getPassword());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, postData, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        queue.add(request);
        Intent intent = new Intent(CreateAccountActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}
