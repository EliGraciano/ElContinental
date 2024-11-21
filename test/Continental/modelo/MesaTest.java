package Continental.modelo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.rmi.RemoteException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MesaTest {

    IMesa mesa;

    @BeforeEach
    void setUp(){
        mesa = new Mesa();

    }

    @Test
    void comenzarJuego() throws RemoteException {
        mesa.altaJugador("Elias");
        assertEquals(false, mesa.canEmpezarRonda());
        assertEquals(true, mesa.canAgregarJugador());
        mesa.altaJugador("Nicolas");
        assertEquals(true, mesa.canEmpezarRonda());
        mesa.iniciarRonda();
        mesa.robarDelMazo();
        mesa.descartar(0);
        mesa.robarDelPozo();
        mesa.descartar(2);
        mesa.robarDelMazo();
        mesa.descartar(0);
        mesa.robarDelMazo();
        mesa.descartar(0);
        mesa.robarDelPozo();
        mesa.descartar(0);
        mesa.robarDelMazo();
        mesa.descartar(0);
        ArrayList<Carta> juego = new ArrayList<>();
        juego.add(new Carta(1,Palo.DIAMANTE));
        juego.add(new Carta(1,Palo.DIAMANTE));
        juego.add(new Carta(1,Palo.PICA));
        ArrayList<Carta> juego2 = new ArrayList<>();
        juego2.add(new Carta(5,Palo.DIAMANTE));
        juego2.add(new Carta(5,Palo.TREBOL));
        juego2.add(new Carta(5,Palo.CORAZON));
        mesa.bajarJuegos(juego,juego2);






    }

}