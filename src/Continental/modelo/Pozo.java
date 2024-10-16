package Continental.modelo;

import java.util.ArrayList;
import java.util.Random;

public class Pozo extends ConjuntoDeCartas{
    private ArrayList<Carta> cartas;

    public Pozo() {
        this.cartas = new ArrayList<>();
    }

    public int getSize(){
        //devuelvo e tamanio del mazo
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

    public Carta robar(){
        //genero un valor random entre 1 y el tamanio del mazo y saco esa carta
        if (this.getSize() == 0){
            return null;
        }
        Random random = new Random();
        int indiceCarta = random.nextInt(this.getSize());
        Carta cartarobada = this.cartas.get(indiceCarta);
        this.cartas.remove(indiceCarta);
        return cartarobada;
    }

}
