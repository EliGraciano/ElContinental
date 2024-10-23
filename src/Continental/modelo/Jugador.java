package Continental.modelo;

import java.util.ArrayList;

public class Jugador {

    private String nombre;

    private Mano mano;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.mano = new Mano();
    }

    public String getNombre() {
        //devuelve el nombre del jugador
        return this.nombre;
    }

    public ArrayList getMano(){
        // devuelve las cartas de la mano
        return this.mano.getCartas();
    }

    public void descartar(int pos,Pozo pozo){
        // tira al pozo que se le pasa la carta elegida
        Carta cartaDescarte = this.mano.descartar(pos);
        pozo.agregar(cartaDescarte);
    }

    public void robar(Mazo mazo){
        //roba una carta del mazo y la agrega a la mano
        Carta carta = mazo.robar();
        this.mano.agregar(carta);
    }

    public void ordenarMano(){
        this.mano.ordenar();
    }




}
