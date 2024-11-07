package Continental.modelo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

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
        mesa.robarDelPozo();
        mesa.descartar(5);
        mesa.robarDelMazo();
        mesa.descartar(2);
        mesa.robarDelMazo();
        mesa.descartar(1);
        mesa.robarDelMazo();
        mesa.descartar(5);
        mesa.robarDelMazo();
        mesa.descartar(5);
        mesa.robarDelPozo();
        Jugador player1 = new Jugador("Joaquin");
        ArrayList<Carta> cartasMano = new ArrayList<>();
        cartasMano.add(new Carta(3,Palo.DIAMANTE));
        cartasMano.add(new Carta(2,Palo.DIAMANTE));
        cartasMano.add(new Carta(1,Palo.DIAMANTE));
        Mano mano = new Mano(cartasMano);
        player1.setMano(mano);
        mesa.respuestaRobarPozo(true,player1);


    }

}