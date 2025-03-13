package Continental.controlador;

import Continental.modelo.eventos.Evento;
import Continental.modelo.juego.Mesa;
import Continental.interfaces.IObservador;
import Continental.interfaces.IVista;
import Continental.vista.consola.TipoPanel;
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
            case JUGADORAGREGADO -> {vista.mostrarMensaje("\n" + evento.getContenido() + " se unio al juego!");} //if (jugador.equals(evento.getContenido())) que solo muestre agregado con exito si es la vista que tiene que ser
            case CAMBIOTURNO -> {
                if (!(jugador.equals(mesa.getTurno()))){
                    vista.mostrarMensaje("es el turno de: " + evento.getContenido());
                    vista.mostrarMensaje("------- ESPERANDO AL JUGADOR: " + mesa.getTurno() + " --------");
                    vista.mostrarCartasConMazoYConPozo();
                } else {
                    vista.menuTurno();
                }
            }

            case ULTIMARONDA -> {vista.mostrarMensaje("es ultima ronda: ubica todas las cartas que puedas");}
            case JUEGOCOMENZADO -> {
                String turnoActual = mesa.getTurno();
                vista.mostrarMensaje("ronda: " + mesa.getNroRondaActual() + " comenzada");
                if (!jugador.equals(mesa.getTurno())) {
                    vista.mostrarMensaje("turno de: " + turnoActual);
                }
                if (jugador.equals(mesa.getTurno())){
                    vista.menuTurno();
                } else {
                    vista.mostrarMensaje("------- ESPERANDO AL JUGADOR: " + mesa.getTurno() + " --------");
                    vista.mostrarCartasConMazoYConPozo();
                }
            }
            case ACTUALIZARCARTAS -> {
                if (jugador.equals(mesa.getTurno())){
                    vista.menuDescarte();
                } else {
                    vista.mostrarMensaje("------- ESPERANDO AL JUGADOR: " + mesa.getTurno() + " --------");
                    vista.mostrarCartasConMazoYConPozo();
                }
            }
            case PAUSAJUEGO -> {
                if (jugador.equals(mesa.getTurno())) {
                    vista.mostrarMensaje("------- ESPERANDO SI ALGUN JUGADOR QUIERE ROBAR DEL POZO --------");
                }
            }
            //TODO cuando uso el reanudar juego tranquilamente podria usar actualizarcartas(hacen lo mismo)
            case REANUDARJUEGO -> {
                if (jugador.equals(mesa.getTurno())){
                    vista.menuDescarte();
                } else {
                    vista.mostrarMensaje("------- ESPERANDO Al JUGADOR: " + mesa.getTurno() + " SE DESCARTE --------");
                    vista.mostrarCartasConMazoYConPozo();
                }
            }
            case PREGUNTARROBARPOZO -> {
                if (!jugador.equals(mesa.getTurno())){
                    vista.menuFueraDeTurno();
                }
            }

        }

    }

    public void registrarUsuario(String input) {
        try {
            mesa.altaJugador(input);
            this.jugador = input;
            vista.menuInicio();
        } catch (Exception e){
            vista.mostrarMensaje(e.getMessage());
            vista.inicio();
        }
    }

    public void inicarRonda() {
        try {
            mesa.canEmpezarRonda();
            mesa.iniciarRonda();
        } catch (Exception e) {
            vista.mostrarMensaje(e.getMessage());
        }
    }

    public ArrayList<String> cartasManoUsuario()  {
        ArrayList<String> cartas = mesa.cartasManoJugadorToString(this.jugador);
        if (cartas == null){
            vista.mostrarMensaje("Jugador no encontrado");
        }
        return cartas;
    }

    public void descartarCarta(String pos) throws Exception{
        try {
            int numero = Integer.parseInt(pos);
            mesa.descartar(numero-1);
        } catch (Exception e) {
            throw new Exception(e);
            //throw new Exception("porfavor ingrese un numero valido");
        }
    }


    public String mostrarPozo(){
            return this.mesa.getCartaTopePozo();
    }

    public void robarMazo() {
        mesa.robarDelMazo();
    }

    public void robarPozo() {
        try {
        mesa.robarDelPozo();
        } catch (Exception e){
        vista.mostrarMensaje(e.getMessage());
        }
    }

    public void robarFueraDeTurnoJugador(boolean resultado) {
        mesa.respuestaRobarPozo(resultado,this.jugador);
    }

    public String getJugador() {
        return this.jugador;
    }
}
