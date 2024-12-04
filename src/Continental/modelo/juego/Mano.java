package Continental.modelo.juego;

import Continental.modelo.cartas.*;

import java.util.ArrayList;
import java.util.Comparator;

public class Mano extends ConjuntoDeCartas {

    public Mano(ArrayList<Carta> cartas) {
        this.cartas = cartas;
    }

    public Carta descartar(int pos) throws IndexOutOfBoundsException{
        Carta cartaselec = this.cartas.get(pos);
        this.cartas.remove(pos);
        return cartaselec;
    }

    public void agregar(Carta carta){
        this.cartas.add(carta);
    }

    public IJuego bajarJuego(ArrayList<Carta> cartas) throws IllegalArgumentException{
        // devuelvo el conjunto de cartas a bajar y las saco de la mano
        // TODO cambiar por una excepcion el if
        IJuego juego = combinacionValida(cartas);
        for (Carta carta : cartas) {
            this.cartas.remove(carta);
        }
        return juego;
    }

    public void ordenar() {
        // se ordenan por su valor
        this.cartas.sort(Comparator.comparingInt(Carta::getValor));
    }

    private IJuego combinacionValida(ArrayList<Carta> cartas){
        // creo una instancia de validador para chequear que el juego a bajar sea valido
        // (y devuelvo una instancia del juego creado(para ponerla en la mesa))
        IValidador validador = new ValidadorEscalera();
        if (validador.esValida(cartas)){
            return new Escalera(cartas);
        }
        validador = new ValidadorTercia();
        if (validador.esValida(cartas)){
            return new Tercia(cartas);
        }
        throw new IllegalArgumentException("Las cartas no forman una combinación válida.");
    }

    public void ubicar(int pos, IJuego juego) throws IndexOutOfBoundsException{
        //TODO aca recibo un booleano que podria usar para indicar si se ubico con exito o no
        Carta cartaselec = this.cartas.get(pos);
        if (juego.acomodar(cartaselec)){
            this.cartas.remove(pos);
        }
    }

    public void ubicarPorMono(int pos, IJuego juego){
        Carta cartaselec = this.cartas.get(pos);
        Carta cartaCambiada = juego.cambiarPorMono(cartaselec);
        if (cartaCambiada != null){
            this.cartas.remove(pos);
            this.cartas.add(cartaCambiada);
        }
    }

    public int calcularPuntosEnMano(){
        int puntos = 0;
        for (Carta carta: this.cartas){
            carta.getPuntos(puntos);
        }
        return puntos;
    }

    public ArrayList<String> manoToString(){
        ArrayList<String> manoCartas = new ArrayList<>();
        for (Carta carta : this.cartas){
            manoCartas.add(carta.toString());
        }
        return manoCartas;
    }



}
