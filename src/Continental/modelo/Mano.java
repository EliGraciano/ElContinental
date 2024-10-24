package Continental.modelo;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;

public class Mano extends ConjuntoDeCartas {
    private ArrayList<ICombinacion> combinaciones;

    public Mano() {
        //TODO como manejo la creacion de la lista de combinaciones?
        this.cartas = new ArrayList<>();
    }

    public Carta descartar(int pos){
        Carta cartaselec = this.cartas.get(pos);
        this.cartas.remove(pos);
        return cartaselec;
    }

    public void agregar(Carta carta){
        this.cartas.add(carta);
    }

    public ArrayList bajarCartas(ArrayList<Carta> cartas){
        // devuelvo el conjunto de cartas a bajar y las saco de la mano
        // TODO cambiar por una excepcion el if
        if (combinaciones.get(0).esValida(cartas) || combinaciones.get(1).esValida(cartas)) {
            ArrayList<Carta> cartasBajadas = new ArrayList<>();
            for (Carta carta : cartas) {
                cartasBajadas.add(carta);
                this.cartas.remove(carta);
            }
            return cartasBajadas;
        }
        else return null;
    }

    public void ordenar() {
        // se ordenan por su valor
        this.cartas.sort(Comparator.comparingInt(Carta::getValor));
    }
}
