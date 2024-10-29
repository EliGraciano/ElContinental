package Continental.modelo;

import java.util.ArrayList;

public abstract class ConjuntoDeCartas {

    protected ArrayList<Carta> cartas;

    public int getSize(){
        //devuelvo el tamanio del mazo
        return this.cartas.size();
    }

    public ArrayList<Carta> getCartas(){
        //devuelve la lista de cartas(mazo)
        //TODO retornar otro array para conservar el encapsulamiento y no dañar el array original
        //return new ArrayList<>(this.cartas);
        return this.cartas;
    }

}
