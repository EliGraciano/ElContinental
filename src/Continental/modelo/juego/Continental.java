package Continental.modelo.juego;

import Continental.controlador.Controlador;
import Continental.vista.IVista;
import Continental.vista.consola.Consola;

public class Continental {
    public static void main(String[] args) {

        IVista vista = new Consola();
        IVista vista2 = new Consola();
        Controlador contraladorJuego = new Controlador(vista);
        Controlador controladorJuego2 = new Controlador(vista2);
        vista.iniciar();
        vista2.iniciar();
    }

}
