package Continental.modelo;

import java.util.ArrayList;

public abstract class ConjuntoDeCartas {

    protected ArrayList<Carta> cartas;

    public int getSize(){
        //devuelvo el tamanio del mazo
        int cont = 0;
        for (int i = 0; i < cartas.size(); i++){
            cont++;
        }
        return cont;
    }

    public ArrayList<Carta> getCartas(){
        //devuelve la lista de cartas(mazo)
        return this.cartas;
    }

}
