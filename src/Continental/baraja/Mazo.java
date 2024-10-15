package Continental.baraja;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Mazo {
    private ArrayList<Carta> cartas;

    public Mazo() {
        this.cartas = new ArrayList<>();
        generarCartas(this.cartas);
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

    private void generarCartas(ArrayList<Carta> mazo){
        // genero todas las cartas que va a tener el mazo
        for (Palo palo : Palo.values()){
            for(int i = 1; i < 13; i++){
                this.cartas.add(new Carta(i,palo));
            }
        }
    }

    public void mezclar(){
        //como el nombre dicce,mezcla las cartas
        Collections.shuffle(this.cartas);
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
