package Continental.modelo.cartas;

import java.util.ArrayList;

public abstract class ConjuntoDeCartas {

    protected ArrayList<Carta> cartas;

    public int getSize(){
        //devuelvo el tamaño del mazo
        return this.cartas.size();
    }

    public ArrayList<Carta> getCartas(){
        //devuelve la lista de cartas(mazo) copiadas en otro array para conservar el encapsulamiento(se produce aliasing)
        return new ArrayList<>(this.cartas);
    }

}
