package Continental.modelo;

import java.util.ArrayList;

public class Mano extends ConjuntoDeCartas {

    public Mano() {
        this.cartas = new ArrayList<>();
    }

    public Carta tirar(int pos){
        Carta cartaselec = this.cartas.get(pos);
        this.cartas.remove(pos);
        return cartaselec;
    }

    public void agregar(Carta carta){
        this.cartas.add(carta);
        //this.cartas.sort();
    }

}
