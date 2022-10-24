package GameStates;

import static java.lang.Thread.sleep;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tfg.R;
import com.example.tfg.main.Main.MainActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import GamePack.Game;
import GamePack.GameLoop;

public class Playing extends AppCompatActivity{

    private boolean borrado = false;

    public static String clase;
    private TextView textView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playing);

        textView = findViewById(R.id.txtClase);

        textView.setText(clase);

        MainActivity.gameState = "Playing";

    }

    public void borrar(View view){

        SharedPreferences clase = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = clase.edit();
        editor.clear();
        editor.apply();

        borrado = true;

        Toast.makeText(this, "Datos borrados", Toast.LENGTH_SHORT).show();
    }

    private void setClase(String setClase) {

        SharedPreferences clase = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor miEditor = clase.edit();
        miEditor.putString("clase", setClase);
        miEditor.apply();
    }

    public void onPause(){
        super.onPause();

        if(!borrado){
            setClase(clase);

            SharedPreferences gameState = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor miEditor = gameState.edit();
            miEditor.putString("gameState", MainActivity.gameState);
            miEditor.apply();
        }

    }

    //ABRE MENU AL PULSAR ATRAS//
    @Override public void onBackPressed() {


    }
}
