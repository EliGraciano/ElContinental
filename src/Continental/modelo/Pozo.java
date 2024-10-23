package Continental.modelo;

import java.util.ArrayList;
import java.util.Random;

public class Pozo extends ConjuntoDeCartas{
    private ArrayList<Carta> cartas;

    public Pozo() {
        this.cartas = new ArrayList<>();
    }

    public int getSize(){
        //devuelvo el tamanio del pozo
        int cont = 0;
        for (int i = 0; i < cartas.size(); i++){
            cont++;
        }
        return cont;
    }

    public ArrayList<Carta> getCartas(){
        //devuelve la lista de cartas(pozo)
        return this.cartas;
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
