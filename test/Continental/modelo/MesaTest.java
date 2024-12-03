package Continental.modelo;

import Continental.modelo.cartas.Carta;
import Continental.modelo.cartas.Palo;
import Continental.modelo.juego.Mesa;
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
    void comenzarJuego()  {

        try {
            mesa.altaJugador("Elias");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        assertEquals(false, mesa.canEmpezarRonda());
        assertEquals(true, mesa.canAgregarJugador());
        try {
            mesa.altaJugador("Nicolas");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
        juego.add(new Carta(1, Palo.DIAMANTE));
        juego.add(new Carta(1,Palo.DIAMANTE));
        juego.add(new Carta(1,Palo.PICA));
        ArrayList<Carta> juego2 = new ArrayList<>();
        juego2.add(new Carta(5,Palo.DIAMANTE));
        juego2.add(new Carta(5,Palo.TREBOL));
        juego2.add(new Carta(5,Palo.CORAZON));
        mesa.bajarJuegos(juego,juego2);






    }

}