package Continental.modelo;

import java.util.ArrayList;

public class Ronda {
    private int cartasADar;

    private int nroTercias;

    private int nroEscaleras;


    public Ronda(int nroRonda) {
        switch (nroRonda){
            case 1:
                this.cartasADar = 6;
                this.nroTercias = 2;
                this.nroEscaleras = 0;
                break;
            case 2:
                this.cartasADar = 7;
                this.nroTercias = 1;
                this.nroEscaleras = 1;
                break;
            case 3:
                this.cartasADar = 8;
                this.nroTercias = 0;
                this.nroEscaleras = 2;
                break;
            case 4:
                this.cartasADar = 9;
                this.nroTercias = 3;
                this.nroEscaleras = 0;
                break;
            case 5:
                this.cartasADar = 10;
                this.nroTercias = 2;
                this.nroEscaleras = 1;
                break;
            case 6:
                this.cartasADar = 11;
                this.nroTercias = 1;
                this.nroEscaleras = 2;
                break;
            case 7:
                this.cartasADar = 12;
                this.nroTercias = 0;
                this.nroEscaleras = 3;
                break;
        }
    }

    public int getCartasADar(){
        return cartasADar;
    }

    public int getJuegosABajar(){
        return this.nroEscaleras + this.nroTercias;
    }

}
