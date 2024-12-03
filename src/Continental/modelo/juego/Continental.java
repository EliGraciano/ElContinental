package Continental.modelo.juego;

import Continental.controlador.Controlador;
import Continental.vista.IVista;
import Continental.vista.consola.Consola;

public class Continental {
    public static void main(String[] args) {
        Mesa mesa = new Mesa();
        IVista vista = new Consola();
        Controlador contraladorJuego = new Controlador(vista);

        vista.iniciar();
    }

}
