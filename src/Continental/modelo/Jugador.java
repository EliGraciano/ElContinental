package Continental.modelo;

import java.util.ArrayList;

public class Jugador {

    private final String nombre;

    private ArrayList<Carta> mano;

    public Jugador(String nombre) {
        this.nombre = nombre;
        mano = new ArrayList<Carta>();
    }

    public String getNombre() {
        return nombre;
    }

    public ArrayList<Carta> getMano() {
        return mano;
    }

    public void robar(Mazo mazo){
        Carta carta = mazo.robar();
        this.mano.add(carta);
    }

    public void robarPozo(Pozo pozo){
        Carta carta = pozo.robar();
        this.mano.add(carta);
    }

    public void descartar(Carta carta,Pozo pozo){
        pozo.agregar(carta);
    }




}
