package Continental.modelo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
class CombinacionesTest {
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
//        for (int i = 0; i < 2; i++) {
//            proyectoE.add(new Carta(i+1,Palo.DIAMANTE));}
//        }

        proyectoE.add(new Carta(50,Palo.MONO));
        proyectoE.add(new Carta(2,Palo.DIAMANTE));
        proyectoE.add(new Carta(50,Palo.MONO));
        proyectoE.add(new Carta(4,Palo.DIAMANTE));


        proyectoT.add(new Carta(7,Palo.DIAMANTE));
        proyectoT.add(new Carta(8,Palo.MONO));
        proyectoT.add(new Carta(7,Palo.DIAMANTE));

        Juego escalera = new Escalera(proyectoE);

        System.out.println("ESCALERA:");
        for (Carta carta : proyectoE){
            System.out.println(carta.toString()+",");
        }
        System.out.println("TERCIA:");
        for (Carta carta : proyectoT){
            System.out.println(carta.toString()+",");
        }
        ArrayList<Carta> modeloMano = new ArrayList<>();
        modeloMano.add(new Carta(1,Palo.DIAMANTE));
        modeloMano.add(new Carta(3,Palo.DIAMANTE));
        Mano mano = new Mano(modeloMano);

        Jugador player = new Jugador("Elias");
        player.setMano(mano);

        player.ubicarPorMono(0,escalera);

        System.out.println("ESCALERA:");
        for (Carta carta : proyectoE){
            System.out.println(carta.toString()+",");
        }
    }

    @Test
    void comprobarValidaciones() {
        assertEquals(true,iValidadorT.esValida(proyectoT));
        assertEquals(true,iValidadorE.esValida(proyectoE));
        //TODO EL ERRORE ESTA EN QUE COMPARO DOS VECES LA MISMA CARTA PERO EL CONTADOR SE SUMA
        System.out.println(iValidadorT.esValida(proyectoT));
        System.out.println(iValidadorE.esValida(proyectoE));
    }
}