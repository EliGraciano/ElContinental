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

    public Juego bajarJuego(ArrayList<Carta> cartas){
        // devuelvo el conjunto de cartas a bajar y las saco de la mano
        // TODO cambiar por una excepcion el if
        Juego juego = combinacionValida(cartas);
        if (juego != null) {
            //ArrayList<Carta> cartasBajadas = new ArrayList<>();
            for (Carta carta : cartas) {
                //cartasBajadas.add(carta);
                this.cartas.remove(carta);
            }
            return juego;
        }
        else return null;
    }

    public void ordenar() {
        // se ordenan por su valor
        this.cartas.sort(Comparator.comparingInt(Carta::getValor));
    }

    private Juego combinacionValida(ArrayList<Carta> cartas){
        // creo una instancia de validador para chequear que el juego a bajar sea valido
        // (y devuelvo una isntancia dle juego creado(para ponerla en la mesa))
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

    public void ubicar(int pos,Juego juego){
        //TODO aca recibo un booleano que podria usar para indicar si se ubico con exito o no
        Carta cartaselec = this.cartas.get(pos);
        if (juego.acomodar(cartaselec)){
            this.cartas.remove(pos);
        }
    }

    public void ubicarPorMono(int pos,Juego juego){
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



}
