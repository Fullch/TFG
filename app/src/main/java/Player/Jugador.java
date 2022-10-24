package Player;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;

import GameStates.GameStates;

public class Jugador implements Serializable {

    String nombre, clase;
    GameStates state;

    public Jugador(){
        this.nombre = "";
        this.clase = "";
        this.state = GameStates.INICIO;
    }

    public Jugador(String nombre, String clase, GameStates state){
        this.nombre = nombre;
        this.clase = clase;
        this.state = state;
    }

    public void cargarPartida(Jugador jugador) throws IOException, ClassNotFoundException {
        //Carga la partida guardada en el objeto jugador vacio que se crea al principio del programa

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("partida.save"));
        jugador = (Jugador) ois.readObject();
        ois.close();
    }

    public void guardarPartida(Jugador jugador) throws IOException {
        //Guarda partida en un fichero objeto

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("partida.save"));
        oos.writeObject(jugador);
        oos.flush();
        oos.close();
    }
}
