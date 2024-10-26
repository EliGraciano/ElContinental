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
        return this.cartas;
    }

}
