package Continental.modelo;

import java.util.ArrayList;

public class ValidadorTercia implements IValidador{

    @Override
    public boolean esValida(ArrayList<Carta> cartas) {
        // me fijo que todas las cartas sean del mismo numero, ya que no importa si hay palos repetidos, o Monos(puede ser una tercia de monos)
        if (cartas.size() < 3) {return false;} // si tiene 3 o mas funciona
        int valorCartas = cartas.getFirst().getValor();
        for (Carta carta : cartas){
            if (carta.getPalo() != Palo.MONO) {
                if (valorCartas != carta.getValor()) {
                    return false;
                }
            }
        }
        return true;
    }
}
