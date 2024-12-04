package Continental.interfaces;

import Continental.modelo.cartas.Carta;
import Continental.modelo.cartas.IJuego;
import Continental.modelo.eventos.Evento;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IMesa extends IObservable {
    String getTurno() throws RemoteException;

    // que reciba un numero (cantidad de jugadores) y en base a este decida cuantos mazos crear
    boolean canEmpezarRonda() ;

    boolean canAgregarJugador() ;

    boolean canBajar2Juegos() ;

    boolean canBajar3Juegos() ;

    void iniciarRonda() ;

    void altaJugador(String nombre) ;

    void bajarJuegos(ArrayList<Carta> primerJuego, ArrayList<Carta> segundoJuego) ;

    void bajarJuegos(ArrayList<Carta> primerJuego, ArrayList<Carta> segundoJuego, ArrayList<Carta> terecerJuego) ;

    void ubicarCarta(int pos, IJuego juego) ;

    void ubicarPorMono(int pos, IJuego juego) ;

    void descartar(int pos) ;

    void robarDelPozo() ;

    void saltarTurno();

    void robarDelMazo() ;

    void respuestaRobarPozo(boolean respuesta, String jugador) ;

    void notificar(Evento evento);
}
