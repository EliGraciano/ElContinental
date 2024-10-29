package Continental.modelo;

import java.util.ArrayList;

public class Mesa {
    //donde transcurrira el juego?, ronda o en mesa?

    Mazo mazo;

    Pozo pozo;

    ArrayList<Jugador> jugadores;

    // que reciba un numero (cantidad de jugadores) y en base a este decida cuantos mazos crear
    public Mesa(int cantJugadores) {
        // TODO si la cantidad de jugadores es igual o menor a 4, poder usar grafica, si es mayor usar vista consola
        if (cantJugadores < 5){
            this.mazo = new Mazo(2);
            this.mazo.mezclar();
            this.jugadores = crearJugadores(cantJugadores);
        }
        else if (cantJugadores > 5 && cantJugadores < 8){
            this.mazo = new Mazo(3);
            this.mazo.mezclar();
            // como hago que me pasen el nombre desde la vista?
            this.jugadores = crearJugadores(cantJugadores);
        }
        else {throw new RuntimeException();}
    }

    private ArrayList<Jugador> crearJugadores(int cantJugadores){
        ArrayList<Jugador> jugadoresCreados = new ArrayList<>();
        for (int i = 0; i < cantJugadores; i++){
            jugadoresCreados.add(altaJugador("Pasar nombre aca"));
        }
        return jugadoresCreados;
    }

    private Jugador altaJugador(String nombre){
        return new Jugador(nombre);
    }

    public void repartir(Ronda ronda){
        for (Jugador jugador : this.jugadores){
            Mano mano = this.mazo.darAJugador(ronda.getCartasADar());
            jugador.setMano(mano);
        }
    }











}
