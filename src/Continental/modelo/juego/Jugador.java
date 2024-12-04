package Continental.modelo.juego;

import Continental.modelo.cartas.IJuego;
import Continental.modelo.cartas.Carta;
import Continental.modelo.cartas.Mazo;
import Continental.modelo.cartas.Pozo;

import java.util.ArrayList;

public class Jugador {
// (cada ronda va a ser distinta y me sirve pasarle el validador como parametro en base a las reglas de cada ronda)
    private final String nombre;

    private Mano mano;

    private boolean interrumpe; // true si quiere interrumpir, false si no quiere interrumpir(que tenga una tecla que sea robar)

    private boolean juegoBajado; // booleano para saber si el jugador ya bajo sus juegos correspondientes

    private int puntos;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.puntos = 0;
        this.interrumpe = false;
        this.juegoBajado = false;
    }

    public String getNombre() {
        //devuelve el nombre del jugador
        return this.nombre;
    }

    public ArrayList<Carta> getMano(){
        // devuelve las cartas de la mano
        return this.mano.getCartas();
    }

    public int getPuntos() {
        return puntos;
    }

    public boolean isJuegoBajado() {
        return juegoBajado;
    }

    public boolean isInterrumpe() {
        return interrumpe;
    }

    public void setMano(Mano mano) {
        this.mano = mano;
    }

    public void setInterrumpe(boolean interrumpe) {
        this.interrumpe = interrumpe;
    }

    public void setJuegoBajado(boolean juegoBajado) {
        this.juegoBajado = juegoBajado;
    }

    public void addPuntos() {
        int puntosSumados = this.mano.calcularPuntosEnMano();
        this.puntos += puntosSumados;
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

    public void robar(Pozo pozo){
        //roba una carta del mazo y la agrega a la mano
        Carta carta = pozo.robar();
        this.mano.agregar(carta);
    }

    public void interrumpirTurno(){
        // funcion que invocan los jugadores cuando estan fuera de su turno para poder robar del pozo
        this.setInterrumpe(true);
    }

    public void robarFueraDeTurno(Mazo mazo,Pozo pozo){
        //roba una carta del pozo fuera de turno y una del mazo como castigo y las agrega a la mano
        Carta cartaMazo = mazo.robar();
        this.mano.agregar(cartaMazo);
        Carta cartaPozo = pozo.robar();
        this.mano.agregar(cartaPozo);
    }


    public IJuego bajarJuego(ArrayList<Carta> cartas) throws IllegalArgumentException{
        // bajo el juego
        IJuego juego = this.mano.bajarJuego(cartas);
        this.setJuegoBajado(true);
        return juego;
    }

    //hacer otro ordenar mano para que el usario mueva las cartas y se ordenen como quiere el usuario
    public void ordenarMano(){
        // mecanismo de ordenamiento por valor de carta
        this.mano.ordenar();
    }

    public void ubicar(int pos, IJuego juego){
        this.mano.ubicar(pos,juego);
    }

    public void ubicarPorMono(int pos, IJuego juego){
        this.mano.ubicarPorMono(pos, juego);
    }

    @Override
    public String toString() {
        return this.nombre;
    }
}
