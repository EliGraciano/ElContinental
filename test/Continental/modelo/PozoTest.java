package Continental.modelo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PozoTest {

    Pozo pozo;

    @BeforeEach
    void setUp(){
        pozo = new Pozo();
        agregar();
    }

    @Test
    void agregar() {
        this.pozo.agregar(new Carta(10,Palo.DIAMANTE));
        this.pozo.agregar(new Carta(9,Palo.DIAMANTE));
        this.pozo.agregar(new Carta(8,Palo.DIAMANTE));
    }

    @Test
    void robarPozo(){
        Carta cartaRobada = this.pozo.robar();
        System.out.println("carta:" + cartaRobada.toString());
    }


}