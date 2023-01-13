package com.example.bloodmenstrue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class bienvenue extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenue);

        //rediriger vers la page principale "MAinActivity" après 2 secondes.

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                //demarrer une page.
                Intent intent =new Intent(getApplicationContext(),welcome.class);
                startActivity(intent);
                finish();
            }
        };
        //handler post delayer(temps de lanimation).
        new Handler().postDelayed(runnable, 2000);

    }
}