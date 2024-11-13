package Continental.modelo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MesaTest {

    IMesa IMesa;

    @BeforeEach
    void setUp(){
        IMesa = new Mesa();

    }

    @Test
    void comenzarJuego() {
        IMesa.altaJugador("Elias");
        assertEquals(false, IMesa.canEmpezarRonda());
        assertEquals(true, IMesa.canAgregarJugador());
        System.out.println("resultado : "+ IMesa.canAgregarJugador());
        System.out.println("resultado : "+ IMesa.canEmpezarRonda());
        IMesa.altaJugador("Nicolas");
        assertEquals(true, IMesa.canAgregarJugador());
        assertEquals(true, IMesa.canEmpezarRonda());
        System.out.println("resultado : "+ IMesa.canEmpezarRonda());
        IMesa.iniciarRonda();
        System.out.println("turno: " + IMesa.getTurno(true));
        IMesa.repartir();
        IMesa.robarDelPozo();
        IMesa.descartar(5);
        IMesa.robarDelMazo();
        IMesa.descartar(2);
        IMesa.robarDelMazo();
        IMesa.descartar(1);
        IMesa.robarDelMazo();
        IMesa.descartar(5);
        IMesa.robarDelMazo();
        IMesa.descartar(5);
        IMesa.robarDelPozo();
        Jugador player1 = new Jugador("Joaquin");
        ArrayList<Carta> cartasMano = new ArrayList<>();
        cartasMano.add(new Carta(3,Palo.DIAMANTE));
        cartasMano.add(new Carta(2,Palo.DIAMANTE));
        cartasMano.add(new Carta(1,Palo.DIAMANTE));
        Mano mano = new Mano(cartasMano);
        player1.setMano(mano);
        IMesa.respuestaRobarPozo(true,player1);


    }

}