package Continental.modelo;

import java.util.ArrayList;
import java.util.Comparator;

public class Mano extends ConjuntoDeCartas {

    public Mano(ArrayList<Carta> cartas) {
        this.cartas = cartas;
    }

    public Carta descartar(int pos){
        Carta cartaselec = this.cartas.get(pos);
        this.cartas.remove(pos);
        return cartaselec;
    }

    public void agregar(Carta carta){
        this.cartas.add(carta);
    }

    public ArrayList<Carta> bajarCartas(ArrayList<Carta> cartas){
        // devuelvo el conjunto de cartas a bajar y las saco de la mano
        // TODO cambiar por una excepcion el if
        if (combinacionValida(cartas) != null) {
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

    private ConjuntoDeCartas combinacionValida(ArrayList<Carta> cartas){
        // creo una instancia de validador para chequear que las combinaciones sean validas
        // (y devuelvo la combinacion
        IValidador validador = new ValidadorEscalera();

        if (validador.esValida(cartas)){
            return new Escalera(cartas);
        }
        validador = new ValidadorTercia();
        if (validador.esValida(cartas)){
            return new Tercia(cartas);
        }
        return null;
    }
}
