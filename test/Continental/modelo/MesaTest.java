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
        assertEquals(true, mesa.canAgregarJugador());
        System.out.println("resultado : "+ mesa.canAgregarJugador());
        System.out.println("resultado : "+ mesa.canEmpezarRonda());
        mesa.altaJugador("Nicolas");
        assertEquals(true, mesa.canAgregarJugador());
        assertEquals(true, mesa.canEmpezarRonda());
        System.out.println("resultado : "+ mesa.canEmpezarRonda());
        mesa.iniciarRonda();
        System.out.println("turno: " + mesa.getTurno(true));
        mesa.repartir();
        mesa.roboDelPozo();
        mesa.descartar(5);
        mesa.roboDelMazo();
        mesa.descartar(2);
        mesa.roboDelMazo();
        mesa.descartar(1);
        mesa.roboDelMazo();
        mesa.descartar(5);
        mesa.roboDelMazo();
        mesa.descartar(5);
        mesa.roboDelPozo();

    }

}