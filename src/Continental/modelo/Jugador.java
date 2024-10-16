package Continental.modelo;

import java.util.ArrayList;

public class Jugador {

    private String nombre;

    private Mano mano;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.mano = new Mano();
    }

    public void tirar(int pos){
        mano.tirar(pos);
    }

    public void robar(Carta carta){
        mano.agregar(carta);
    }

    


}
