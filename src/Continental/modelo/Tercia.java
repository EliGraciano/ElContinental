package Continental.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Tercia implements ICombinacion{


    @Override
    public boolean esValida(ArrayList<Carta> cartas) {
        // pongo todos los palos en un array, y por cada carta sumo 1 al palo en dicho array, y al salir ninguno tiene que tener mas de 1,sino hay 2 cartas de un mismo palo
        if (cartas.size() != 3) {return false;}
        int valorCartas = cartas.getFirst().getValor();
        int[] contadorPalos = new int[Palo.values().length]; // creo un arreglo de ints con el tamaño de los palos posibles
        for (Carta carta : cartas){
            if(valorCartas != carta.getValor()){
                return false;
            }
            Palo palo = carta.getPalo();
            contadorPalos[palo.ordinal()]++;
        }
        for (int count : contadorPalos) {
            if (count > 1) {
                return false; // Si algún palo aparece más de una vez, no es tercia
            }
        }
        return true;
    }
    
}
