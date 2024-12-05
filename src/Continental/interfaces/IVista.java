package Continental.interfaces;

import Continental.modelo.juego.Mesa;

public interface IVista {

    void iniciar();

    void mostrarMensaje(String s);

    void setModelo(Mesa mesa);

    void menuInicio();

    void menuTurno();

    void menuDescarte();

    void menuFueraDeTurno();

    void menuJuegoBajado();

    void mostrarCartasConMazoYConPozoYJuegosEnMesa();

    void actualizarCartas();
}
