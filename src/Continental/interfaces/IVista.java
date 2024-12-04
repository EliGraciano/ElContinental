package Continental.interfaces;

import Continental.modelo.juego.Mesa;

public interface IVista {

    void iniciar();

    void mostrarMensaje(String s);

    void jugarTurno();

    void setModelo(Mesa mesa);
}
