package Continental.modelo;

import java.util.ArrayList;
import java.util.Random;

public class Pozo extends ConjuntoDeCartas{
    private ArrayList<Carta> cartas;

    public Pozo() {
        this.cartas = new ArrayList<>();
    }

    public Carta robar(){
        //robo la primera carta
        if (this.getSize() == 0){
            return null;
        }
        int ultimacarta = this.getSize();
        Carta cartarobada = this.cartas.get(ultimacarta);
        this.cartas.remove(ultimacarta);
        return cartarobada;
    }

    public void agregar(Carta carta){
        this.cartas.add(carta);
    }

}
