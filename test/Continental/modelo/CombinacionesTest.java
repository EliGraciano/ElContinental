package Continental.modelo;

import Continental.modelo.cartas.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
class CombinacionesTest {
    Mazo mazo;
    Escalera escalera;
    IValidador iValidadorE;
    ArrayList<Carta> proyectoE;


    @BeforeEach
    void setUp(){
        mazo =new Mazo(2);
        //iValidadorT = new ValidadorTercia();
        iValidadorE = new ValidadorEscalera();

        proyectoE = new ArrayList<>();
        //proyectoT = new ArrayList<>();



        //proyectoE.add(new Carta(1,Palo.DIAMANTE));
        proyectoE.add(new Carta(50, Palo.MONO));
        proyectoE.add(new Carta(50,Palo.MONO));
        //proyectoE.add(new Carta(2,Palo.DIAMANTE));
        proyectoE.add(new Carta(3,Palo.DIAMANTE));
        proyectoE.add(new Carta(4,Palo.DIAMANTE));
        //proyectoE.add(new Carta(5,Palo.DIAMANTE));


        System.out.println("ESCALERA:");
        for (Carta carta : proyectoE){
            System.out.println(carta.toString()+",");
        }


    }

    @Test
    void comprobarValidaciones() {
        assertEquals(true,iValidadorE.esValida(proyectoE));
        IJuego escalera = new Escalera(proyectoE);



//        ArrayList<Carta> modeloMano = new ArrayList<>();
//        modeloMano.add(new Carta(1,Palo.DIAMANTE));
//        modeloMano.add(new Carta(3,Palo.DIAMANTE));
//        Mano mano = new Mano(modeloMano);
//
//        Jugador player = new Jugador("Elias");
//        player.setMano(mano);
//
//        player.ubicarPorMono(0,escalera);
//
//        System.out.println("ESCALERA:");
//        for (Carta carta : proyectoE){
//            System.out.println(carta.toString()+",");
//        }
//
//        System.out.println(iValidadorE.esValida(proyectoE));
    }
}