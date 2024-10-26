package Continental.modelo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.annotation.processing.SupportedAnnotationTypes;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MazoTest {

    Mazo mazo;

    @BeforeEach
    void setUp(){
        mazo = new Mazo();

    }

    @Test
    public void mostrarMazo(){
        for (Carta carta : this.mazo.getCartas()){
            System.out.println(carta.toString());
        }
        assertEquals(54,this.mazo.getSize());
        System.out.println("tamaño de la baraja = " + this.mazo.getSize());
        Carta cartarobada = this.mazo.robar();
        assertEquals(53,this.mazo.getSize());
        System.out.println("tamaño de la baraja = " + this.mazo.getSize());
        System.out.println("carta robada = " + cartarobada);
    }


}