package Continental.modelo;

import java.util.ArrayList;

public class Mano extends ConjuntoDeCartas {

    public Mano() {
        this.cartas = new ArrayList<>();
    }

    public Carta descartar(int pos){
        Carta cartaselec = this.cartas.get(pos);
        this.cartas.remove(pos);
        return cartaselec;
    }

    public void agregar(Carta carta){
        this.cartas.add(carta);
        // puedo ordenar a medida que voy a gregando?
        //this.cartas.sort();
    }

    public void ordenar() {
        //this.cartas.sort();

    }
}
