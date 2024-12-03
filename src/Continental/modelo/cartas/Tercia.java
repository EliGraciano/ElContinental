package Continental.modelo.cartas;

import java.util.ArrayList;

public class Tercia extends ConjuntoDeCartas implements IJuego {

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

    @Override
    // sobre carga para poder acomodar una carta en un juego dado
    public boolean acomodar(Carta carta){
        //TODO cambiar por excpecion
        if (this.valorTercia() == carta.getValor()){
            this.cartas.add(carta);
            return true;
        }
        return false;
    }

    @Override
    public Carta cambiarPorMono(Carta carta){
        if (carta.isValor(this.valorTercia())){
            return sacarMono(carta);
        }
        return null;
    }

    private Carta sacarMono(Carta carta){
        for (Carta carta1 : this.cartas){
            if (carta1.isPalo(Palo.MONO)){
                this.cartas.add(carta);
                return carta1;
            }
        }
        return null;
    }



}
