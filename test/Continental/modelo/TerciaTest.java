package Continental.modelo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TerciaTest {
    Mazo mazo;
    Tercia tercia;
    Escalera escalera;
    IValidador iValidadorT;
    IValidador iValidadorE;
    ArrayList<Carta> proyectoE;
    ArrayList<Carta> proyectoT;

    @BeforeEach
    void setUp(){
        mazo =new Mazo(2);
        iValidadorT = new ValidadorTercia();
        iValidadorE = new ValidadorEscalera();

        proyectoE = new ArrayList<>();
        proyectoT = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            proyectoT.add(new Carta(i+1,Palo.DIAMANTE));
        }
        for (int i = 0; i < 4; i++) {
            proyectoE.add(mazo.robar());
        }
        System.out.println("ESCALERA:");
        for (Carta carta : proyectoE){
            System.out.println(carta.toString()+",");
        }
        System.out.println("TERCIA:");
        for (Carta carta : proyectoT){
            System.out.println(carta.toString()+",");
        }
    }

    @Test
    void comprobarvalidaciones() {
        System.out.println(iValidadorE.esValida(proyectoE));
        System.out.println(iValidadorT.esValida(proyectoT));
    }
}