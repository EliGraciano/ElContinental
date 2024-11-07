package Continental.modelo;

import Continental.utilidades.IObservable;
import Continental.utilidades.IObservador;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Mesa implements IObservable {
    //TODO hacer que puedan cambiar en una cambinacion una carta por un mono(la carta que reemplazaria el mono)
    //TODO NOTIFICAR CUANDO EMPIEZA LA RONDA QUE JUEGOS HAY QUE BAJAR
    //TODO hacer un atributo booleano que se llame robo para saber si ya robo la carta y no permitirle robar mas
    private Mazo mazo;

    private Pozo pozo;

    private Queue<Jugador> jugadores;

    private ArrayList<Juego> juegosEnMesa;

    private Ronda rondaActual;

    private int nroRondaActual = 0;

    private Jugador turno;

    private ArrayList<IObservador> observadores;

    private boolean yaRobo;


    public Mesa() {
        // como hago que me pasen el nombre desde la vista?
        this.observadores = new ArrayList<>();
        this.jugadores = new LinkedList<>();
    }

    public Jugador getTurno(){
        return this.jugadores.peek();
    }

    public String getTurno(boolean truee){
        return this.turno.toString();
    }

    // que reciba un numero (cantidad de jugadores) y en base a este decida cuantos mazos crear
    public boolean canEmpezarRonda(){
        //le devuelve a la vista si se puede empezar  o no la ronda para poder poenr le button en enabled
        return this.jugadores.size() >= 2;

    }

    public boolean canAgregarJugador(){
        //le devuelve a la vista si se puede seguir agregando jugadores
        return this.jugadores.size() <= 8;
    }

    public void iniciarRonda(){
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
        notificar(new Evento(TipoEvento.COMENZARJUEGO));
    }

    public void altaJugador(String nombre){
        //TODO notificar a la vista que muestre que se unio dicho jugador
        this.jugadores.add(new Jugador(nombre));
        notificar(new Evento(TipoEvento.AGREGARJUGADOR));
    }

    public void repartir(){
        for (Jugador jugador : this.jugadores){
            Mano mano = this.mazo.darAJugador(this.rondaActual.getCartasADar());
            jugador.setMano(mano);
        }
        this.pozo.agregar(this.mazo.robar());
    }

    public void bajarJuegos(ArrayList<Carta> juegosABajar){

    }

    public void ubicarCarta(int pos,Juego juego) {
        turno.ubicar(pos,juego);  // le paso la posicion de la carta a ubicar y el juego en donde ubicarlo
        //TODO va a notificar para que la vista muestre
    }

    public void descartar(int pos){
        this.turno.descartar(pos, this.pozo);
        //notificar(new Evento.chequearcartas));
        terminarTurno();
    }

    public void roboDelPozo(){
        //chequear que no roben mas de una carta
        this.turno.robar(this.pozo);
    }

    private void terminarTurno(){
        Jugador poll = this.jugadores.poll();
        this.jugadores.add(poll);
        this.yaRobo = false;
        notificar(new Evento(TipoEvento.CAMBIARTURNO));
    }

    public void roboDelMazo(){
        //chequear que no me roben mas de una carta
        turno.robar(this.mazo);
        interrumpir();
    }

    private void interrumpir(){
        //me fijo ordenadamente si algun jugador quiere robar el pozo
        notificar(new Evento(TipoEvento.PAUSARJUEGO));
        if (this.jugadores.peek() == turno){
            this.jugadores.add(this.jugadores.poll());
            notificar(new Evento(TipoEvento.REANUDARJUEGO));
        }else{
            Jugador player = this.jugadores.poll();
            this.jugadores.add(player);
            //TODO hacer TOString en jugador
            notificar(new Evento(TipoEvento.PREGUNTARROBARPOZO,player.toString()));
        }
    }

    public void respuestaRobarPozo(boolean respuesta, Jugador jugador ){
        if (respuesta){
            jugador.robarFueraDeTurno(this.mazo,this.pozo);
        }else{
            interrumpir();
        }
    }

    private ArrayList<Integer> finRonda(){
        //recorro todos lso jugadores y sumo los puntos de las cartas que tienen en la mano
        //usar el getPuntos para ver con cuantos puntos queda cada jugador
        ArrayList<Integer> puntosAntes = new ArrayList<>();
        for (Jugador jugador : this.jugadores){
            puntosAntes.add(jugador.getPuntos());
            jugador.addPuntos();
        }
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

    private void ubicarCartasFinRonda(Jugador jugador){

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



//    public Evento jugarTurno(Evento evento){
//        // me fijo que hizo en su turno(si quiere robar del mazo,del pozo, si quiere bajar, o si ya no puede bajar y esta en la otra instancia)
//        Jugador jugador = jugadores.poll();
//        if (!jugador.isJuegoBajado()) {
//            if (evento != Evento.BAJARJUEGO) {
//                //  le pido al controlador que me de la posicion de la carta a descartar(hiria le scanner)
//                if (evento == Evento.ROBO_POZO) {
//                    return roboDelPozo(jugador, 1);
//                } else if (evento == Evento.ROBO_MAZO) {
//                    return roboDelMazo(jugador,1);
//                }
//            } else {
//                // pedir cartas a bajar al controlador que seleccione cualquiera de los juegos a bajar
//                this.juegosEnMesa.add(jugador.bajarJuego(jugador.getMano())); // primer juego
//                this.juegosEnMesa.add(jugador.bajarJuego(jugador.getMano())); // segundo juego
//                if (rondaActual.getNroRonda() >= 4){
//                    this.juegosEnMesa.add(jugador.bajarJuego(jugador.getMano())); // tercer juego
//                }
//                return Evento.FINTURNO;
//            }
//        } else {
//            //  le pido al controlador que me de la posicion de la carta a descartar(hiria le scanner)
//            juegosBajados(evento,jugador,pos);
//            // lo que puede hacer si ya bajo todos los juegos(robar y ubicar)
//        }
//        if (jugador.getMano().isEmpty()){
//            // hacer un metodo que permita a cada jugador ubicar sus cartas, y luego de eso llame a la funcion contar puntos
//            ArrayList<Integer> puntos = finRonda();
//            //mostrar to do lo de puntos
//            return Evento.FINRONDA;
//            // debo poder darle tiempo a los jugadores para que cada uno ubique sus cartas
//            //se llama al metodo fin ronda que calcula los puntos de cada jugador y los suma a cada uno //  hacer que se muestre primero la lista de puntos y luego sume
//        }
//        this.jugadores.add(jugador);
//        return Evento.FINTURNO;
//
//    }

//    private void juegosBajados(Evento evento, Jugador jugador, int cartaDescarte){
//        switch (evento){
//            case ROBO_MAZO -> roboDelMazo(jugador,cartaDescarte);
//            case UBICARCARTA -> ubicarCarta(jugador,juego,cartaDescarte);
//            case ROBO_POZO -> roboDelPozo(jugador,cartaDescarte);
//        }
//    }
//
//    private void juegosNoBajados(Evento evento, Jugador jugador, int cartaDescarte){
//        switch (evento){
//            case ROBO_MAZO -> roboDelMazo(jugador,cartaDescarte);
//            case BAJARJUEGO -> bajarJuegos(jugador,juegos,cartaDescarte);
//            case ROBO_POZO -> roboDelPozo(jugador,cartaDescarte);
//        }
//
//    }

//    public void robarFueraDeTurno(Jugador jugador){
//        //si algun jugador no esta en turno, y desea robar, invoca este metodo, el cual en base a su situacion actual en la partida decide si puede interrumpir o no
//        if (!jugador.isJuegoBajado()){
//            jugador.setInterrumpe(true);
//        }
//        else{
//            // inchequeable preguntar
//            int posicion = new ArrayList<>(this.jugadores).indexOf(jugador);
//            if (posicion == 1){ // me fijo que el jugador sea el siguiente que va a tener el turno
//                jugador.setInterrumpe(true);
//            }
//        }
//    }
//

}
