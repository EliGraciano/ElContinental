package Continental.controlador;

import Continental.modelo.eventos.Evento;
import Continental.modelo.juego.Mesa;
import Continental.utilidades.IObservador;
import Continental.vista.IVista;
import Continental.vista.grafica.VistaJugador;

public class Controlador implements IObservador {

    private final IVista vista;

    private Mesa mesa;

    private VistaJugador vist;

    public Controlador(IVista vista) {
        this.vista = vista;
        this.mesa = new Mesa();
        try {
            mesa.agregarObservador(this);
        } catch (Exception e){
            vista.MostrarMensaje(e.toString());
        }
    }


    @Override
    public void update(Evento evento) {
        switch (evento.getTipo()){
            case JUGADORAGREGADO -> {
                vista.MostrarMensaje("Jugador Agregado con exito!");
            }

        }

    }

    public void registrarUsuario(String input) {
        try {
            mesa.altaJugador(input);
        } catch (Exception e){
            vista.MostrarMensaje(e.toString());
        }
    }
}
