package Continental.modelo;

import java.util.ArrayList;
import java.util.Random;

public class Pozo extends ConjuntoDeCartas{

    public Pozo() {
        this.cartas = new ArrayList<>();
    }

    public Carta robar(){
        //robo la primera carta
        //int ultimacarta = this.getSize();
        //TODO TENIA UN PROBLEMA DE INDICES
        Carta cartarobada = this.cartas.getLast();
        this.cartas.remove(this.cartas.getLast());
        return cartarobada;
    }

    public void agregar(Carta carta){
        this.cartas.add(carta);
    }

}
