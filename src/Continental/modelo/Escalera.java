package Continental.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class Escalera  implements ICombinacion{

    @Override
    public boolean esValida(ArrayList<Carta> cartas){
        if (cartas.size() < 4){return false;} // si la escalera tiene menos de 4 cartas ya no funciona
        Collections.sort(cartas, Comparator.comparingInt(Carta::getValor));
        int cartavalor = cartas.getFirst().getValor();
        Palo paloEscalera = cartas.getFirst().getPalo();
        Boolean esComodin = false;
        for (Carta carta : cartas){
            if (esComodin && carta.getPalo() == Palo.MONO){return false;} // si dos comodines estan juntos tiro false
            if (carta.getPalo() == Palo.MONO  ) { // si es un comodin no chequeo nada
                esComodin = true;
            } else { // si no es un comodin
                esComodin = false;
                paloEscalera = carta.getPalo();
                if (carta.getValor() != cartavalor || carta.getPalo() != paloEscalera) { // me fijo que sean el mismo palo y respeten la escalera
                    return false;
                }
            }
            cartavalor++; // sumo uno para ver si es una escalera
        }
        return true;
    }

}
