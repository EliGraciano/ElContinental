package Continental.modelo;

import ar.edu.unlu.rmimvc.observer.IObservableRemoto;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IMesa extends IObservableRemoto {
    String getTurno() throws RemoteException;

    // que reciba un numero (cantidad de jugadores) y en base a este decida cuantos mazos crear
    boolean canEmpezarRonda() throws RemoteException;

    boolean canAgregarJugador() throws RemoteException;

    boolean canBajar2Juegos() throws RemoteException;

    boolean canBajar3Juegos() throws RemoteException;

    void iniciarRonda() throws RemoteException;

    void altaJugador(String nombre) throws RemoteException;

    void repartir() throws RemoteException;

    void bajarJuegos(ArrayList<Carta> primerJuego, ArrayList<Carta> segundoJuego) throws RemoteException;

    void bajarJuegos(ArrayList<Carta> primerJuego, ArrayList<Carta> segundoJuego, ArrayList<Carta> terecerJuego) throws RemoteException;

    void ubicarCarta(int pos, Juego juego) throws RemoteException;

    void ubicarPorMono(int pos, Juego juego) throws RemoteException;

    void descartar(int pos) throws RemoteException;

    void robarDelPozo() throws RemoteException;

    void robarDelMazo() throws RemoteException;

    void respuestaRobarPozo(boolean respuesta, String jugador) throws RemoteException;

    void notificarObservadores(Object arg);
}
