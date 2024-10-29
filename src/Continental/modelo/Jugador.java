package Continental.modelo;

import java.util.ArrayList;

public class Jugador {
//TODO clase Juego o Mesa son lo mismo, voy a tener una herencia de rondas
// (cada ronda va a ser distinta y me sirve pasarle el validador como parametro en base a las reglas de cada ronda)
    private final String nombre;

    private Mano mano;

    private boolean Turno;

    public Jugador(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        //devuelve el nombre del jugador
        return this.nombre;
    }

    public ArrayList<Carta> getMano(){
        // devuelve las cartas de la mano
        return this.mano.getCartas();
    }

    public boolean isTurno() {
        return Turno;
    }

    public void setTurno(boolean esTurno) {
        this.Turno = esTurno;
    }

    public void setMano(Mano mano) {
        this.mano = mano;
    }

    public void descartar(int pos, Pozo pozo){
        // tira al pozo que se le pasa la carta elegida
        Carta cartaDescarte = this.mano.descartar(pos);
        pozo.agregar(cartaDescarte);
    }

    public void robar(Mazo mazo){
        //roba una carta del mazo y la agrega a la mano
        Carta carta = mazo.robar();
        this.mano.agregar(carta);
    }

    public void bajarCartas(ArrayList<Carta> cartas){
        // TODO esto me esta devolviendo un array de cartas que puedo utilizar
        this.mano.bajarCartas(cartas);
    }

    public void ordenarMano(){
        // mecanismo de ordenamiento por valor de carta
        this.mano.ordenar();
    }




}
