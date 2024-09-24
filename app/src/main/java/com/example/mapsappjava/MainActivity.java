//Controls basic menu screen which allows user to choose login or create account
package com.example.mapsappjava;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;



public class MainActivity extends AppCompatActivity {

    //Loads the MenuActivity view
    @Override
    protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_menu);

     }

    //Switches to Login Activity view
    public void switchToLogin(View V){
        Intent loginIntent = new Intent(this, LoginActivity.class);
        startActivity(loginIntent);
    }

    //Switches to Create Account Activity view
    public void switchToCreateAccount(View V){
       Intent createAccountIntent = new Intent(this, CreateAccountActivity.class);
       startActivity(createAccountIntent);
    }
}
