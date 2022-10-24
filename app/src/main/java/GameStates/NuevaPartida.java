package GameStates;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;

import com.example.tfg.R;

public class NuevaPartida extends AppCompatActivity {

    ImageButton botonCab;
    ImageButton botonLad;
    ImageButton botonMago;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nueva_partida);

        botonCab = (ImageButton) findViewById(R.id.btnCaballero);
        botonLad = (ImageButton) findViewById(R.id.btnLadron);
        botonMago = (ImageButton) findViewById(R.id.btnMago);

        botonCab.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    //System.out.println("Pulsado");
                    botonCab.setImageResource(R.drawable.button_inicio_caballero2);
                    botonCab.setImageResource(R.drawable.button_inicio_caballero3);

                }else if(event.getAction() == MotionEvent.ACTION_UP){
                    //System.out.println("soltado");
                    botonCab.setImageResource(R.drawable.button_inicio_caballero);
                    Playing.clase = "Caballero";
                    Inicio.partidaCreada = false;
                    llamarPlaying(view);
                }
                return false;
            }
        });

        botonLad.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    //System.out.println("Pulsado");
                    botonLad.setImageResource(R.drawable.button_inicio_ladron2);
                    botonLad.setImageResource(R.drawable.button_inicio_ladron3);

                }else if(event.getAction() == MotionEvent.ACTION_UP){
                    //System.out.println("soltado");
                    botonLad.setImageResource(R.drawable.button_inicio_ladron);
                    Playing.clase = "Ladron";
                    Inicio.partidaCreada = false;
                    llamarPlaying(view);
                }
                return false;
            }
        });

        botonMago.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    //System.out.println("Pulsado");
                    botonMago.setImageResource(R.drawable.button_inicio_mago2);
                    botonMago.setImageResource(R.drawable.button_inicio_mago3);

                }else if(event.getAction() == MotionEvent.ACTION_UP){
                    //System.out.println("soltado");
                    botonMago.setImageResource(R.drawable.button_inicio_mago);
                    Playing.clase = "Mago";
                    Inicio.partidaCreada = false;
                    llamarPlaying(view);
                }
                return false;
            }
        });
    }

    public void llamarPlaying(View view){
        Intent intent = new Intent(this, Playing.class);
        startActivity(intent);
    }

    //BLOQUEA BACK//
    @Override public void onBackPressed() { }
}