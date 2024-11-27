package Continental.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Mazo extends ConjuntoDeCartas{


    public Mazo() {
        this.cartas = new ArrayList<>();
        generarCartas(this.cartas);
    }

    public Mazo(int cantidadMazos) {
        this.cartas = new ArrayList<>();
        for (int i = 0; i< cantidadMazos;i++) {
            generarCartas(this.cartas);
        }
    }

    private void generarCartas(ArrayList<Carta> mazo){
        //sacar el parametro y utilizar el this(acceder al aributo cartas)
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

    public Mano darAJugador(int cantCartas){
        // funcion que saca una cantidad de cartas del mazo
        ArrayList<Carta> cartas = new ArrayList<>();
        for (int i = 0;i < cantCartas;i++){
            Carta carta = this.robar();
            cartas.add(carta);
        }
        return new Mano(cartas);
    }



}
