package com.example.migadepan.pharmacyapp;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent actividad_main = new Intent(getApplicationContext(), IntroMapsActivity.class);
        startActivity(actividad_main);
    }
}
