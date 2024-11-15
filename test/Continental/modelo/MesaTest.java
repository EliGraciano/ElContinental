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





    }

}