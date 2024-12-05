package Continental.modelo.juego;

import Continental.controlador.Controlador;
import Continental.interfaces.IVista;
import Continental.vista.consola.Consola;

public class Continental {
    public static void main(String[] args) {
        Mesa mesa1 = new Mesa();
        IVista vista = new Consola();
        IVista vista2 = new Consola();
        //IVista vista3 = new Consola();
        vista.setModelo(mesa1);
        vista2.setModelo(mesa1);
        //vista3.setModelo(mesa1);
        vista.iniciar();
        vista2.iniciar();
        //vista3.iniciar();
    }

}
