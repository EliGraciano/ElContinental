package Continental.controlador;

import Continental.modelo.eventos.Evento;
import Continental.modelo.juego.Mesa;
import Continental.utilidades.IObservador;
import Continental.vista.IVista;
import Continental.vista.grafica.VistaJugador;

public class Controlador implements IObservador {

    private final IVista vista;

    private final Mesa mesa;

    private VistaJugador vist;

    public Controlador(IVista vista) {
        this.vista = vista;
        this.mesa = new Mesa();
        try {
            mesa.agregarObservador(this);
        } catch (Exception e){
            vista.mostrarMensaje(e.toString());
        }
    }


    @Override
    public void update(Evento evento) {
        switch (evento.getTipo()){
            case JUGADORAGREGADO -> {
                vista.mostrarMensaje("Jugador Agregado con exito!");
            }

        }

    }

    public void registrarUsuario(String input) {
        try {
            mesa.altaJugador(input);
        } catch (Exception e){
            vista.mostrarMensaje(e.toString());
        }
    }

    public void inicarRonda() throws Exception {
        if (!mesa.canEmpezarRonda()){
            throw new Exception("se necesita al menos un jugador mas para comenzar la ronda");
        } else {
            mesa.iniciarRonda();
            String turnoActual = mesa.getTurno();
            vista.mostrarMensaje("ronda: " + mesa.getNroRondaActual() + " comenzada");
            vista.mostrarMensaje("turno de: "+turnoActual);
        }
    }
}
