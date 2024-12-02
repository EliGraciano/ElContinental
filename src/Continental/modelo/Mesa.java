package Continental.modelo;

import Continental.utilidades.IObservable;
import Continental.utilidades.IObservador;
import ar.edu.unlu.rmimvc.observer.ObservableRemoto;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Mesa implements IObservable {
    //TODO NOTIFICAR CUANDO EMPIEZA LA RONDA QUE JUEGOS HAY QUE BAJAR
    //TODO al principio de cada ronda preguntar cuantos juegos hay que bajar, para saber cual de las 2 funciones habilitar
    private Mazo mazo;

    private Pozo pozo;

    private Queue<Jugador> jugadores;

    private ArrayList<Juego> juegosEnMesa;

    private Ronda rondaActual;

    private int nroRondaActual = 0;

    private Jugador turno;

    private Jugador jugadorATerminar;

    private ArrayList<IObservador> observadores;

    private boolean yaRobo; //centinela que si es true, la vista ya no le apague el boton de robar


    public Mesa() {
        // como hago que me pasen el nombre desde la vista?
        this.observadores = new ArrayList<>();
        this.jugadores = new LinkedList<>();
    }


    public String getTurno() {
        return this.turno.toString();
    }

    // que reciba un numero (cantidad de jugadores) y en base a este decida cuantos mazos crear

    public boolean canEmpezarRonda() {
        //le devuelve a la vista si se puede empezar  o no la ronda para poder poenr le button en enabled
        return this.jugadores.size() >= 2;

    }


    public boolean canAgregarJugador() {
        //le devuelve a la vista si se puede seguir agregando jugadores
        return this.jugadores.size() < 8;
    }


    public int getJuegosABajar(){
        //en base al numero de ronda se podra utilizar una de las 2 opcionas para bajar
        return (nroRondaActual <=3) ? 2 : 3;
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
        turno = this.jugadores.poll();
        this.jugadores.add(turno);
        this.jugadorATerminar = null;
        repartir();
        notificar(new Evento(TipoEvento.COMENZOJUEGO));
    }


    public void altaJugador(String nombre) {

        this.jugadores.add(new Jugador(nombre));
        notificar(new Evento(TipoEvento.AGREGUEJUGADOR));
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
        Juego juego1 = turno.bajarJuego(primerJuego);
        Juego juego2 = turno.bajarJuego(segundoJuego);
        this.juegosEnMesa.add(juego1);
        this.juegosEnMesa.add(juego2);
    }


    public void bajarJuegos(ArrayList<Carta> primerJuego, ArrayList<Carta> segundoJuego, ArrayList<Carta> terecerJuego) throws IllegalArgumentException{
        //va a haber rondas q va a bajar 3 juegos
        Juego juego1 =turno.bajarJuego(primerJuego);
        Juego juego2 = turno.bajarJuego(segundoJuego);
        Juego juego3 = turno.bajarJuego(terecerJuego);
        this.juegosEnMesa.add(juego1);
        this.juegosEnMesa.add(juego2);
        this.juegosEnMesa.add(juego3);
    }


    public void ubicarCarta(int pos, Juego juego) throws IllegalStateException {
        try {
            turno.ubicar(pos, juego); // Operación que podría fallar
            // Notificar a la vista para que actualice
            notificar(new Evento(TipoEvento.CARTAUBICADA));
        } catch (IllegalArgumentException e) {
            // Relanzar con un mensaje más descriptivo
            throw new IllegalStateException("Error al ubicar la carta en el juego: posición o juego inválido.", e);
        }
    }


    public void ubicarPorMono(int pos, Juego juego) throws IllegalStateException {
        try {
            turno.ubicar(pos, juego); // Operación que podría fallar
            // Notificar a la vista para que actualice
            notificar(new Evento(TipoEvento.CARTAUBICADA));
        } catch (IllegalArgumentException e) {
            throw new IllegalStateException("Error al ubicar el comodín en el juego: posición o juego inválido.", e);
        }
    }


    public void descartar(int pos) {
        this.turno.descartar(pos, this.pozo);
        notificar(new Evento(TipoEvento.CARTADESCARTADA));
        terminarTurno();
    }


    public void robarDelPozo() throws IllegalStateException {
        try {
            //chequear que no roben mas de una carta
            this.turno.robar(this.pozo);
            this.yaRobo = true;
        } catch (IllegalStateException e) {
            throw new IllegalStateException("el pozo no tiene cartas.", e);
        }
    }

    private void terminarTurno(){
        Jugador poll = this.jugadores.poll();
        if (esFinRonda()) {
            //en este momento los jugadores no se pueden descartar mas cartas(solo ubicar)
            this.jugadorATerminar = poll;
            this.turno = poll;
            notificar(new Evento(TipoEvento.ULTIMARONDA));
        }
        this.jugadores.add(turno);
        this.turno = poll;
        this.yaRobo = false;
        notificar(new Evento(TipoEvento.CAMBIOTURNO));

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
    }

    private void interrumpir(){
        //me fijo ordenadamente si algun jugador quiere robar el pozo
        notificar(new Evento(TipoEvento.PAUSAJUEGO));
        if (this.jugadores.peek() == turno){
            this.jugadores.add(this.jugadores.poll());
            notificar(new Evento(TipoEvento.REANUDARJUEGO));
        }else{
            Jugador player = this.jugadores.poll();
            this.jugadores.add(player);
            //hacer TOString en jugador
            notificar(new Evento(TipoEvento.PREGUNTARROBARPOZO,player.toString()));
        }
    }


    public void respuestaRobarPozo(boolean respuesta, String jugador)  {
        //el controlador pasa un string con el nombre del jugador y busca en la lista dicho jugador para no perder encapsulamietno(correcion de santi :))
        Jugador jugaux = buscarjugador(jugador);
        if (respuesta){
            jugaux.robarFueraDeTurno(this.mazo,this.pozo);
            //que termine una vez q robo
            notificar(new Evento(TipoEvento.REANUDARJUEGO));
        }else{
            interrumpir();
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

    @Override
    public void notificar(Evento evento){
        for (IObservador obser : this.observadores){
            obser.update(evento);
        }
    }


    @Override
    public void agregarObservador(IObservador observador) {

    }

    @Override
    public void eliminarObservador(IObservador observador) {

    }

}
