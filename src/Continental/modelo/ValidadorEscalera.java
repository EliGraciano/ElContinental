package Continental.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ValidadorEscalera implements IValidador{
    @Override
    public boolean esValida(ArrayList<Carta> cartas){
        //hay que parametrizar la cantidad mínima de cartas.
        if (cartas.size() < 4 || !this.todasDelMismoPalo(cartas)) { //me fijo q sean del mismo palo y tenga al menos 4 cartas
            return false;
        }
        ArrayList<Carta> copiaCartas = (ArrayList<Carta>) cartas.clone();
        int cantidadMonosSeguidos = 0;
        int valorSiguiente;

        while (!copiaCartas.isEmpty() && (copiaCartas.getFirst().getPalo() == Palo.MONO)) {
            cantidadMonosSeguidos++;
            copiaCartas.removeFirst();
        }
        if (cantidadMonosSeguidos > 1) return false;
        else if (!copiaCartas.isEmpty()) {
            valorSiguiente = copiaCartas.getFirst().getValor() + 1;
            boolean salir = false;
            cantidadMonosSeguidos = 0;
            while(!copiaCartas.isEmpty() && !salir) {
                copiaCartas.removeFirst();
                //Hay que parametrizar el valor del mono
                if (!copiaCartas.isEmpty() && (copiaCartas.getFirst().getValor() == 50 || copiaCartas.getFirst().getValor() == valorSiguiente)) {
                    if (copiaCartas.getFirst().getValor() == 50) {
                        cantidadMonosSeguidos++;
                        if (cantidadMonosSeguidos > 1) return false;
                    } else {
                        cantidadMonosSeguidos = 0;
                    }
                    valorSiguiente++;
                } else salir = true;
            }
            //Se rompió la escalera y quedaron más cartas.
            return copiaCartas.isEmpty();
        } else return true;
    }

    private boolean todasDelMismoPalo(ArrayList<Carta> cartas) {
        Palo paloInicial = null;
        for (Carta carta : cartas) {
            if (!carta.isPalo(Palo.MONO)) {
                if (paloInicial == null) {
                    paloInicial = carta.getPalo(); // Tomamos el palo de la primera carta no MONO
                } else if (!carta.getPalo().equals(paloInicial)) {
                    return false; // Si algún palo no coincide, no es válida
                }
            }
        }

        return true; // Todas las cartas no MONO tienen el mismo palo
    }

}
