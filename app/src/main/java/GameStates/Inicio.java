package GameStates;


import static com.example.tfg.main.Main.MainActivity.gameState;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.tfg.R;

public class Inicio extends AppCompatActivity {

    private ConstraintLayout layout = null;
    private ImageButton botonC = null;
    private ImageButton botonNP = null;
    private ImageButton botonS = null;

    public static boolean partidaCreada;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio);

        layout = (ConstraintLayout) findViewById(R.id.layoutInicio);
        botonC = (ImageButton) findViewById(R.id.btnContinuar);
        botonNP = (ImageButton) findViewById(R.id.btnNueva);
        botonS = (ImageButton) findViewById(R.id.btnSalir);

        comprobarPartida();

        if(partidaCreada){
            botonNP.setVisibility(View.GONE);
        }else{
            botonNP.setVisibility(View.VISIBLE);
        }

        botonC.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    //System.out.println("Pulsado");
                    botonC.setImageResource(R.drawable.button_start_continuar2);
                    botonC.setImageResource(R.drawable.button_start_continuar3);

                }else if(event.getAction() == MotionEvent.ACTION_UP){
                    //System.out.println("soltado");
                    botonC.setImageResource(R.drawable.button_start_continuar1);
                    if(partidaCreada){
                        llamarPlaying(view);
                    }else{
                        Context context = getApplicationContext();
                        Toast.makeText(context, "No hay una partida creada", Toast.LENGTH_SHORT).show();
                    }
                }
                return false;
            }
        });

        botonNP.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    //System.out.println("Pulsado");
                    botonNP.setImageResource(R.drawable.button_start_nueva2);
                    botonNP.setImageResource(R.drawable.button_start_nueva3);

                }else if(event.getAction() == MotionEvent.ACTION_UP){
                    //System.out.println("soltado");
                    botonNP.setImageResource(R.drawable.button_start_nueva);
                    llamarNP(view);
                }
                return false;
            }
        });

        botonS.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    //System.out.println("Pulsado");
                    botonS.setImageResource(R.drawable.button_start_salir2);
                    botonS.setImageResource(R.drawable.button_start_salir3);

                }else if(event.getAction() == MotionEvent.ACTION_UP){
                    //System.out.println("soltado");
                    botonS.setImageResource(R.drawable.button_start_salir);

                    //CIERRA APLICACION (deja como main Inicio y lo cierra)//
                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    intent.addCategory(Intent.CATEGORY_HOME);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
                return false;
            }
        });
    }

    public void llamarNP(View view){

        Intent intent = new Intent(this, NuevaPartida.class);
        startActivity(intent);

    }

    public void llamarPlaying(View view){

        Intent intentPlaying = new Intent(this, Playing.class);
        startActivity(intentPlaying);
    }

    private void comprobarPartida(){

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        gameState = pref.getString("gameState", "Inicio");
        Playing.clase = pref.getString("clase", null);

        if (Playing.clase != null){
            partidaCreada = true;
        }else{
            partidaCreada = false;
        }
    }

}