package Continental.controlador;

import Continental.modelo.eventos.Evento;
import Continental.modelo.juego.Mesa;
import Continental.interfaces.IObservador;
import Continental.interfaces.IVista;
import Continental.vista.grafica.VistaJugador;

import java.util.ArrayList;
import java.util.Enumeration;

public class Controlador implements IObservador {

    private final IVista vista;

    private  Mesa mesa;

    private String jugador;

    public Controlador(IVista vista) {
        this.vista = vista;
    }

    public void setModelo(Mesa mesa){
        try {
            this.mesa = mesa;
            this.mesa.agregarObservador(this);
        } catch (Exception e) {
             vista.mostrarMensaje(e.toString());
        }
    }


    @Override
    public void update(Evento evento) {
        //TODO evento cuando se descarta una carta es al pedo mostrarlo(es mejor un actualizar cartas)
        switch (evento.getTipo()){
            case JUGADORAGREGADO -> {vista.mostrarMensaje("Jugador Agregado con exito!");}
            case CAMBIOTURNO -> {vista.mostrarMensaje("es el turno de: " + evento.getContenido());}
            case ULTIMARONDA -> {vista.mostrarMensaje("es ultima ronda: ubica todas las cartas que puedas");}
            case JUEGOCOMENZADO -> {;
                if (jugador.equals(mesa.getTurno())){
                    vista.menuTurno();
                } else {
                    vista.menuFueraDeTurno();
                }
            }
            case ACTUALIZARCARTAS -> {vista.actualizarCartas();}

        }

    }

    public void registrarUsuario(String input) {
        try {
            mesa.altaJugador(input);
            this.jugador = input;
        } catch (Exception e){
            vista.mostrarMensaje(e.getMessage());
        }
    }

    public void inicarRonda() {
        try {
            mesa.canEmpezarRonda();
            mesa.iniciarRonda();
            String turnoActual = mesa.getTurno();
            vista.mostrarMensaje("ronda: " + mesa.getNroRondaActual() + " comenzada");
            vista.mostrarMensaje("turno de: "+turnoActual);
        } catch (Exception e) {
            vista.mostrarMensaje(e.getMessage());
        }
    }

    public ArrayList<String> cartasManoUsuario()  {
        //TODO sacar la excepcion ya que nunca la tiraria, para poder sacar el return null
        try {
            return mesa.cartasManoJugadorToString(this.jugador);
        } catch (Exception e){
            vista.mostrarMensaje(e.getMessage());
        }
        return null;
    }

    public void descartarCarta(String pos) throws Exception{
        try {
            int numero = Integer.parseInt(pos);
            mesa.descartar(numero);
        } catch (Exception e) {
            throw new Exception("porfavor ingrese un numero valido");
        }

    }

    public String mostrarMazo(){
        return this.mesa.getCartaTopeMazo();
    }

    public String mostrarPozo(){
        return this.mesa.getCartaTopePozo();
    }

    public void robarMazo() {
        mesa.robarDelMazo();
    }

    public void robarPozo() {
        mesa.robarDelPozo();
    }

    public void robarFueraDeTurnoJugador() {
        mesa.respuestaRobarPozo(true,this.jugador);
    }
}
