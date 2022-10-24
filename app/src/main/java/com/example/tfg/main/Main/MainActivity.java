package com.example.tfg.main.Main;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;

import com.example.tfg.R;

import GameStates.Inicio;
import GameStates.Playing;

public class MainActivity extends AppCompatActivity{

    private Button btnStart = null;

    public static String gameState;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);

        btnStart = findViewById(R.id.btnStart);
    }

    public void llamarInicio(View view){

        Intent intentInicio = new Intent(this, Inicio.class);
        startActivity(intentInicio);

    }

    public void onResume(){
        super.onResume();

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        gameState = pref.getString("gameState", "Inicio");
        Playing.clase = pref.getString("clase", null);
    }
}