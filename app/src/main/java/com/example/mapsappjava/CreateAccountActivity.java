package com.example.mapsappjava;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

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

            Log.d("Account", newAccount.accountToString());
        }
    }
}
