package Continental.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Mazo {
    private ArrayList<Carta> cartas;
//herencia conjunto de cartas? (para hacer pozo, mazo, mano del jugador)
    public Mazo() {
        this.cartas = new ArrayList<>();
        generarCartas(this.cartas);
    }

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

    private void generarCartas(ArrayList<Carta> mazo){
        // genero todas las cartas que va a tener el mazo
        int cont = 0;
        for (Palo palo : Palo.values()){
            for(int i = 1; i < 15; i++){
                if (cont < 2) {
                    this.cartas.add(new Carta(i, palo));
                }
                if (i == 14){
                    cont++;
                }
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
