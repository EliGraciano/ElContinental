package Continental.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ValidadorEscalera implements IValidador{

    @Override
    public boolean esValida(ArrayList<Carta> cartas){
        // Verificar que la escalera tenga al menos 4 cartas
        if (cartas.size() < 4) {
            return false;
        }

        // Paso 1: Separar las cartas MONO y las cartas que no son MONO
        ArrayList<Carta> cartasNoMonos = new ArrayList<>();
        ArrayList<Integer> posicionesMonos = new ArrayList<>();

        for (int i = 0; i < cartas.size(); i++) {
            Carta carta = cartas.get(i);
            if (carta.getPalo() == Palo.MONO) {
                // Guardar las posiciones de los MONOS
                posicionesMonos.add(i);
            } else {
                // Guardar las cartas no MONO
                cartasNoMonos.add(carta);
            }
        }

        // Paso 2: Ordenar las cartas no MONO por su valor
        cartasNoMonos.sort((c1, c2) -> Integer.compare(c1.getValor(), c2.getValor()));

        // Paso 3: Reconstruir el ArrayList original con los MONOS en sus posiciones originales
        int indiceCartasNoMonos = 0;
        for (int i = 0; i < cartas.size(); i++) {
            if (posicionesMonos.contains(i)) {
                // Los MONOS se mantienen en las mismas posiciones
                continue;
            } else {
                // Insertamos las cartas no MONO ordenadas
                cartas.set(i, cartasNoMonos.get(indiceCartasNoMonos++));
            }
        }

        // Paso 4: Validar las reglas de la escalera
        Palo paloEscalera = null;
        int monosContados = 0;

        for (int i = 0; i < cartas.size(); i++) {
            Carta carta = cartas.get(i);

            // Verificar que no haya 2 MONOS consecutivos
            if (carta.getPalo() == Palo.MONO) {
                if (i > 0 && cartas.get(i - 1).getPalo() == Palo.MONO) {
                    return false; // No puede haber dos MONOS consecutivos
                }
                monosContados++;
            } else {
                // Si es la primera carta no MONO, definimos el palo de la escalera
                if (paloEscalera == null) {
                    paloEscalera = carta.getPalo();
                } else if (carta.getPalo() != paloEscalera) {
                    return false; // Las cartas deben ser del mismo palo
                }

                // Verificar que las cartas sean consecutivas en valor
                if (i > 0 && carta.getValor() != cartas.get(i - 1).getValor() + 1) {
                    // Se puede usar un MONO para llenar el hueco
                    if (monosContados > 0) {
                        monosContados--;
                    } else {
                        return false; // Las cartas no son consecutivas y no hay MONO disponible
                    }
                }
            }
        }

        return true; // Si pasó todas las verificaciones, la escalera es válida

    }



}
