package com.example.mapsappjava;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;



public class MainActivity extends AppCompatActivity {

   @Override
   protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_menu);

   }

   public void switchToMaps(View v){
       Intent mapIntent = new Intent(this,MapsActivity.class);
       startActivity(mapIntent);
    }

   public void switchToLogin(View V){
       Intent loginIntent = new Intent(this, LoginActivity.class);
       startActivity(loginIntent);
   }

   public void switchToCreateAccount(View V){
       Intent createAccountIntent = new Intent(this, CreateAccountActivity.class);
       startActivity(createAccountIntent);
   }

    public void switchToRoutes(View V){
        Intent createAccountIntent = new Intent(this, RoutesActivity.class);
        startActivity(createAccountIntent);
    }

}
