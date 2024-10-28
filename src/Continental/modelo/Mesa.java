package Continental.modelo;

import java.util.ArrayList;

public class Mesa {

    ConjuntoDeCartas mazo;

    Pozo pozo;

    ArrayList<Jugador> jugadores;

    // que reciba un numero (cantidad de jugadores)
    public Mesa(int cantJugadores) {
        if (cantJugadores < 5){
            this.mazo = new Mazo();

        }
        this.mazo = mazo;
        for (int i = 0; i < cantJugadores; i++){
            //altaJugador();
        }

    }

    private Jugador altaJugador(String nombre){
        return new Jugador(nombre);
    }

}
