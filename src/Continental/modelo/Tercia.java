package Continental.modelo;

import java.util.ArrayList;

public class Tercia extends ConjuntoDeCartas implements Juego{

    public Tercia(ArrayList<Carta> cartas) {
        this.cartas = cartas;
    }

    private int valorTercia(){
        for (Carta carta : this.cartas){
            if (carta.getPalo() != Palo.MONO ){
                return carta.getValor();
            }
        }
        //TODO cambiar el return 0(esta mal)
        return 0;
    }

    // sobre carga para poder acomodar una carta en un juego dado
    public boolean acomodar(Carta carta){
        //TODO cambiar por excpecion
        if (this.valorTercia() == carta.getValor()){
            this.cartas.add(carta);
            return true;
        }
        return false;
    }


    
}
