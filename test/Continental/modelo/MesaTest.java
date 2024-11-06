package Continental.modelo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MesaTest {

    Mesa mesa;

    @BeforeEach
    void setUp(){
        mesa = new Mesa();

    }

    @Test
    void comenzarJuego() {
        mesa.altaJugador("Elias");
        assertEquals(false, mesa.canEmpezarRonda());
        mesa.altaJugador("Nicolas");
        assertEquals(true, mesa.canAgregarJugador());
        //assertEquals(true, mesa.canEmpezarRonda());

    }

}