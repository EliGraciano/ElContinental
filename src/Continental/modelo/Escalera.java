package Continental.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Escalera  extends ConjuntoDeCartas {

    public Escalera(ArrayList<Carta> cartas) {
        this.cartas = cartas;
    }

    // sobre carga para poder acomodar una carta en un juego dado
    public void acomodar(Carta carta){
        this.cartas.add(carta);
    }
    // sobre carga para poder acomodar un conjunto de cartas en un juego dado

    public void acomodar(ArrayList<Carta> cartas){
        for (Carta carta : cartas) {
            this.cartas.add(carta);
        }
    }
}
