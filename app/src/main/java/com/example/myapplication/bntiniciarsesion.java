package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class bntiniciarsesion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bntiniciarsesion);

    }
    public void iniciarSesion(View view){
        Intent i = new Intent(this, INICIARSESION.class);
        startActivity(i);
    }
}