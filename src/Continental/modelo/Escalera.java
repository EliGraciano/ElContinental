package Continental.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class Escalera  implements ICombinacion{

    @Override
    public boolean esValida(ArrayList<Carta> cartas){
        //TODO hacer algoritmo de verificacion de escalera tineen que ser si o si de 4 cartas
        // primero ordeno el array, y despues me fijo como esta ordenado(si el array me queda ordenado continuo es una escalera)
        if (cartas.size() != 4){return false;}
        Collections.sort(cartas, Comparator.comparingInt(Carta::getValor));
        int cartavalor = cartas.getFirst().getValor();
        for (Carta carta : cartas){
            if (carta.getPalo() != Palo.MONO ) {
                Palo paloescalera = cartas.getFirst().getPalo();
            }



        }



        }




        return true;
    }

}
