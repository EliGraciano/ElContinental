package Continental.interfaces;

import Continental.modelo.juego.Mesa;
import Continental.vista.consola.TipoPanel;

public interface IVista {

    void iniciar();

    void mostrarMensaje(String s);

    void setModelo(Mesa mesa);

    void mostrarCartasConMazoYConPozo();

    void menuInicio();

    void inicio();

    void menuTurno();

    void menuDescarte();

    void menuFueraDeTurno();

    void menuJuegoBajado();

    void actualizarCartas();

    void setPanelInput(TipoPanel tipo);
}
