package Continental.vista.consola;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConsolaTest {

    Consola consola;

    @BeforeEach
    void consola() {
        consola = new Consola();

    }

    @Test
    void correrConsola(){
        int contador = 0;
        while (true){
            contador++;
        }

    }
}