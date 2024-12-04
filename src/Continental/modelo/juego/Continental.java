package Continental.modelo.juego;

import Continental.controlador.Controlador;
import Continental.interfaces.IVista;
import Continental.vista.consola.Consola;

public class Continental {
    public static void main(String[] args) {
        Mesa mesa1 = new Mesa();
        IVista vista = new Consola();
        IVista vista2 = new Consola();
        Controlador contraladorJuego = new Controlador(vista);
        Controlador controladorJuego2 = new Controlador(vista2);
        contraladorJuego.setModelo(mesa1);
        controladorJuego2.setModelo(mesa1);
        vista.iniciar();
        vista2.iniciar();
    }

}
