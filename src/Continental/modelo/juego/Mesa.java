package Continental.modelo.juego;

import Continental.modelo.eventos.Evento;
import Continental.modelo.eventos.TipoEvento;
import Continental.modelo.cartas.Carta;
import Continental.modelo.cartas.IJuego;
import Continental.modelo.cartas.Mazo;
import Continental.modelo.cartas.Pozo;
import Continental.interfaces.IObservable;
import Continental.interfaces.IObservador;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Mesa implements IObservable {
    //TODO NOTIFICAR CUANDO EMPIEZA LA RONDA QUE JUEGOS HAY QUE BAJAR
    //TODO al principio de cada ronda preguntar cuantos juegos hay que bajar, para saber cual de las 2 funciones habilitar
    private Mazo mazo;

    private Pozo pozo;

    private Queue<Jugador> jugadores;

    private ArrayList<IJuego> juegosEnMesa;

    private Ronda rondaActual;

    private int nroRondaActual = 0;

    private Jugador turno;

    private Jugador jugadorATerminar;

    private ArrayList<IObservador> observadores;

    private boolean yaRobo; //centinela que si es true, mediante el controlador la vista le apague el boton de robar al turno


    public Mesa() {
        // como hago que me pasen el nombre desde la vista?
        this.observadores = new ArrayList<>();
        this.jugadores = new LinkedList<>();
    }


    public String getTurno() {
        return this.turno.toString();
    }

    // que reciba un numero (cantidad de jugadores) y en base a este decida cuantos mazos crear

    public void canEmpezarRonda() throws Exception{
        //le devuelve a la vista si se puede empezar  o no la ronda para poder poenr le button en enabled
        if (!(this.jugadores.size() >= 2)){
            throw new Exception("se necesitan mas jugadores para poder comenzar el juego");
        }

    }


    public boolean canAgregarJugador() {
        //le devuelve a la vista si se puede seguir agregando jugadores
        return this.jugadores.size() <= 8;
    }


    public int getJuegosABajar(){
        //en base al numero de ronda se podra utilizar una de las 2 opcionas para bajar
        return (nroRondaActual <=3) ? 2 : 3;
    }


    public String getCartaTopePozo() {
        return this.pozo.getCartaTope();
    }

    public int getNroRondaActual(){
        return this.nroRondaActual;
    }


    public void iniciarRonda() {
        // TODO si la cantidad de jugadores es igual o menor a 4, poder usar grafica, si es mayor usar vista consola
        if (nroRondaActual < 7) {
            this.nroRondaActual += 1;
        } else { nroRondaActual = 1; }
        this.pozo = new Pozo();
        this.juegosEnMesa = new ArrayList<>();
        this.rondaActual = new Ronda(nroRondaActual);
        int cantJugadores = jugadores.size();
        if (cantJugadores < 5){
            this.mazo = new Mazo(2);
            this.mazo.mezclar();
        }
        else{
            this.mazo = new Mazo(3);
            this.mazo.mezclar();
        }
        //hacer que los eventos sean una clase(para enviar contenido) hago un new evento aca
        this.turno = this.jugadores.poll();
        this.jugadores.add(turno);
        this.jugadorATerminar = null;
        repartir();
        notificar(new Evento(TipoEvento.JUEGOCOMENZADO));
    }


    private boolean jugadorRepetido(String nombre){
        for (Jugador player : this.jugadores){
            if (player.getNombre().equals(nombre)){
                return false;
            }
        }
        return true;
    }

    public void altaJugador(String nombre) throws Exception{
        //TODO agregar que si el nombre del jugador ya esta tambien crashee
        if (nombre.isBlank() || nombre.isEmpty() ){
            throw new Exception("nombre invalido!");
        }
        if (!jugadorRepetido(nombre)){
            throw new Exception("jugador ya registrado!");
        }
        if (!canAgregarJugador()){
            throw new Exception("ya hay 8 jugadores!");
        }
        this.jugadores.add(new Jugador(nombre));
        notificar(new Evento(TipoEvento.JUGADORAGREGADO,nombre));
    }

    //repartir privado
    private void repartir(){
        for (Jugador jugador : this.jugadores){
            Mano mano = this.mazo.darAJugador(this.rondaActual.getCartasADar());
            jugador.setMano(mano);
        }
        this.pozo.agregar(this.mazo.robar());
    }


    public void bajarJuegos(ArrayList<Carta> primerJuego, ArrayList<Carta> segundoJuego) throws IllegalArgumentException{
        //va a haber rondas que va a bajar 2 juegos pero que las rondas TODO en las que hay 3 juegos, no pueda bajar 2
        IJuego juego1 = turno.bajarJuego(primerJuego);
        IJuego juego2 = turno.bajarJuego(segundoJuego);
        this.juegosEnMesa.add(juego1);
        this.juegosEnMesa.add(juego2);
    }


    public void bajarJuegos(ArrayList<Carta> primerJuego, ArrayList<Carta> segundoJuego, ArrayList<Carta> terecerJuego) throws IllegalArgumentException{
        //va a haber rondas q va a bajar 3 juegos
        IJuego juego1 =turno.bajarJuego(primerJuego);
        IJuego juego2 = turno.bajarJuego(segundoJuego);
        IJuego juego3 = turno.bajarJuego(terecerJuego);
        this.juegosEnMesa.add(juego1);
        this.juegosEnMesa.add(juego2);
        this.juegosEnMesa.add(juego3);
    }


    public void ubicarCarta(int pos, IJuego juego) throws IllegalArgumentException {
        turno.ubicar(pos, juego); // Operación que podría fallar
        // Notificar al controlador para que actualice el estado del a vista
        notificar(new Evento(TipoEvento.CARTAUBICADA));
    }


    public void ubicarPorMono(int pos, IJuego juego) throws IllegalArgumentException {
        turno.ubicar(pos, juego); // Operación que podría fallar
        // Notificar a la vista para que actualice
        notificar(new Evento(TipoEvento.CARTAUBICADA));

    }


    public void descartar(int pos) throws IndexOutOfBoundsException {
        this.turno.descartar(pos, this.pozo);
        terminarTurno();
    }


    public void robarDelPozo() throws Exception {
        //chequear que no roben mas de una carta
        System.out.println("Antes de robar - Pozo: " + this.pozo.getCartaTope() + ", Turno: " + turno);
        this.turno.robar(this.pozo);
        this.yaRobo = true;
        System.out.println("Después de robar - Pozo: " + this.pozo.getCartaTope() + ", Turno: " + turno);
        notificar(new Evento(TipoEvento.ACTUALIZARCARTAS));
    }

    private void terminarTurno(){
        Jugador turnoSiguiente = this.jugadores.poll(); // Sacás al siguiente turno de la cola
        System.out.println("Turno actual antes de cambiar: " + this.turno);

        if (esFinRonda()) {
            // Última ronda: el turno permanece con el último jugador
            this.jugadorATerminar = turnoSiguiente;
            this.turno = turnoSiguiente;
            notificar(new Evento(TipoEvento.ULTIMARONDA));
        } else {
            // Cambiás al siguiente jugador
            this.jugadores.add(turnoSiguiente); // Agregás al jugador que terminó al final de la cola
            this.turno = turnoSiguiente; // Asignás el siguiente turno
        }

        this.yaRobo = false; // Restablecés el estado de robo
        System.out.println("Nuevo turno asignado a: " + this.turno);
        notificar(new Evento(TipoEvento.CAMBIOTURNO, this.turno.getNombre()));

    }


    public void saltarTurno(){
        //se va a invocar cuando alla ubicado sus cartas y toque el boton(o cuando no tenga nada para ubicar y lo toque)
        Jugador poll = this.jugadores.poll();
        if (poll == this.jugadorATerminar){
            //si dio la vuelta(pasaron todos) llamo a la funcion para terminar la ronda
            finRonda();
            notificar(new Evento(TipoEvento.FINRONDA));
        }
        this.turno = poll;
        this.jugadores.add(poll);
    }


    public void robarDelMazo() {
        //chequear que no me roben mas de una carta
        turno.robar(this.mazo);
        this.yaRobo = true;
        interrumpir();
        notificar(new Evento(TipoEvento.ACTUALIZARCARTAS));
    }

    private void interrumpir(){
        notificar(new Evento(TipoEvento.PAUSAJUEGO));
        ArrayList<Jugador> jugadoresPendientes = new ArrayList<>(this.jugadores);
        interrumpirRec(jugadoresPendientes, this.turno);
    }

    private void interrumpirRec(ArrayList<Jugador> jugadoresPendientes, Jugador turnoOriginal) {
        if (jugadoresPendientes.isEmpty()) {
            // Si ya preguntamos a todos y nadie robó, reanudamos el juego
            notificar(new Evento(TipoEvento.REANUDARJUEGO));
            return;
        }

        // Tomamos al primer jugador de la lista
        Jugador jugadorActual = jugadoresPendientes.remove(0);

        // Si es el turno original, terminamos la consulta
        if (jugadorActual.equals(turnoOriginal)) {
            notificar(new Evento(TipoEvento.REANUDARJUEGO));
            return;
        }

        // Preguntamos al jugador si quiere robar del pozo
        notificar(new Evento(TipoEvento.PREGUNTARROBARPOZO, jugadorActual.toString()));
    }


    public void respuestaRobarPozo(boolean respuesta, String jugador)  {
        //el controlador pasa un string con el nombre del jugador y busca en la lista dicho jugador para no perder encapsulamietno(correcion de santi :))
        Jugador jugaux = buscarjugador(jugador);
        if (respuesta){
            jugaux.robarFueraDeTurno(this.mazo,this.pozo);
            //que termine una vez q robo
            notificar(new Evento(TipoEvento.REANUDARJUEGO));
        }else{
            interrumpirRec(new ArrayList<>(this.jugadores), this.turno);
        }
    }

    private Jugador buscarjugador(String nombre){
        for(Jugador jugador : this.jugadores){
            if (jugador.getNombre().equals(nombre)){
                return jugador;
            }
        }
        return null;
    }

    private ArrayList<Integer> finRonda(){
        //recorro todos los jugadores y sumo los puntos de las cartas que tienen en la mano
        //usar el getPuntos para ver con cuantos puntos queda cada jugador
        ArrayList<Integer> puntosAntes = new ArrayList<>();
        for (Jugador jugador : this.jugadores){
            puntosAntes.add(jugador.getPuntos());
            jugador.addPuntos();
        }
        //hacer que vuelva al menu principal y que se pueda continuar con otra ronda

        return puntosRonda(puntosAntes);
    }

    private ArrayList<Integer> puntosRonda(ArrayList<Integer> puntosAntes){
        //funcion que recorre el array de jugadores calculando los puntos que sumaron los jugadores en dicha ronda
        ArrayList<Integer> puntosASumar = new ArrayList<>();
        LinkedList<Jugador> jugadores = new LinkedList<>(this.jugadores);
        for (int i = 0; i < this.jugadores.size() ; i++){
            puntosASumar.add(jugadores.get(i).getPuntos() - puntosAntes.get(i));
        }
        return puntosASumar;
    }

    //usar esta funcion para saber si se termino o no se termino la ronda(llamarla despues de cada turno)
    private boolean esFinRonda(){
        return this.turno.getMano().isEmpty() && this.turno.isJuegoBajado();
    }

    public ArrayList<String> cartasManoJugadorToString(String nombre) {
        for (Jugador player : this.jugadores){
            if (player.toString().equals(nombre)) {
                return player.cartasManoToString();
            }
        }
        return null;
    }

    @Override
    public void notificar(Evento evento){
        for (IObservador obser : this.observadores){
            obser.update(evento);
        }
    }


    @Override
    public void agregarObservador(IObservador observador) throws  Exception  {
        if (this.observadores.contains(observador)){
            throw new Exception("no se pudo agregar el observador");
        } else {
            this.observadores.add(observador);
        }
    }

    @Override
    public void eliminarObservador(IObservador observador)throws  Exception {
        if (!this.observadores.contains(observador)){
            throw new Exception("no se pudo eliminar el observador");
        } else {
            this.observadores.remove(observador);
        }
    }

}
