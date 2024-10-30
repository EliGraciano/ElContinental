package Continental.modelo;

import java.util.ArrayList;

public class Ronda {
    //va a haber 7 rondas
    //TODO falta hacer todas las rondas
    private int cartasADar;

    private int nroRonda;

    private ArrayList<Juego> combinaciones; // combinaciones a formar por ronda

    public Ronda(int nroRonda) {
        this.nroRonda = nroRonda;
        switch (nroRonda){
            case 1:
                this.cartasADar = 6;
                break;
            case 2:
                this.cartasADar = 7;
                break;
            case 3:
                this.cartasADar = 8;
                break;
            case 4:
                this.cartasADar = 9;
                break;
            case 5:
                this.cartasADar = 10;
                break;
            case 6:
                this.cartasADar = 11;
                break;
            case 7:
                this.cartasADar = 12;
                break;
        }
    }

    public int getCartasADar(){
        return cartasADar;
    }

    public int getNroRonda() {
        return nroRonda;
    }
}
