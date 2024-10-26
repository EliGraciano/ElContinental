package Continental.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Mazo extends ConjuntoDeCartas{
//herencia conjunto de cartas? (para hacer pozo, mazo, mano del jugador)
    public Mazo() {
        this.cartas = new ArrayList<>();
        generarCartas(this.cartas);
    }

    private void generarCartas(ArrayList<Carta> mazo){
        // genero todas las cartas que va a tener el mazo
        for (Palo palo : Palo.values()){
            if (palo != Palo.MONO) {
                for (int i = 1; i < 14; i++) {
                    this.cartas.add(new Carta(i, palo));
                }
            }
        }
        this.cartas.add(new Carta(50,Palo.MONO));
        this.cartas.add(new Carta(50,Palo.MONO));
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
