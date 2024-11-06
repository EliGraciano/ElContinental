package Continental.modelo;

import Continental.utilidades.IObservable;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Mesa implements IMesa, IObservable {
    //TODO hacer que puedan cambiar en una cambinacion una carta por un mono(la carta que reemplazaria el mono)
    //TODO que las funciones no devuelvan un EVENTOMAZOPOZO(o explicar porque)
    Mazo mazo;

    Pozo pozo;

    Queue<Jugador> jugadores;

    ArrayList<Juego> juegosEnMesa;

    Ronda rondaActual;

    int nroRondaActual = 0;

    public Mesa(int cantJugadores) {
        // como hago que me pasen el nombre desde la vista?
        this.jugadores = crearJugadores(cantJugadores);
    }

    public Jugador getTurno(){
        return this.jugadores.poll();
    }
    // que reciba un numero (cantidad de jugadores) y en base a este decida cuantos mazos crear

    public void iniciarRonda(){
        // TODO si la cantidad de jugadores es igual o menor a 4, poder usar grafica, si es mayor usar vista consola
        this.nroRondaActual += 1;
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
    }

    private Queue<Jugador> crearJugadores(int cantJugadores){
        Queue<Jugador> jugadoresCreados = new LinkedList<>();
        for (int i = 0; i < cantJugadores; i++){
            jugadoresCreados.add(altaJugador("Pasar nombre aca"));
        }
        return jugadoresCreados;
    }

    private void altaJugador(String nombre){
        this.jugadores.add(new Jugador(nombre));
        notificar();
    }

    public void repartir(Ronda ronda){
        for (Jugador jugador : this.jugadores){
            Mano mano = this.mazo.darAJugador(ronda.getCartasADar());
            jugador.setMano(mano);
        }
        this.pozo.agregar(this.mazo.robar());
    }

    public EventoMazoPozo jugarTurno(EventoMazoPozo evento){
        // me fijo que hizo en su turno(si quiere robar del mazo,del pozo, si quiere bajar, o si ya no puede bajar y esta en la otra instancia)
        Jugador jugador = jugadores.poll();
        if (!jugador.isJuegoBajado()) {
            if (evento != EventoMazoPozo.BAJARJUEGO) {
                // TODO le pido al controlador que me de la posicion de la carta a descartar(hiria le scanner)
                if (evento == EventoMazoPozo.ROBO_POZO) {
                    return roboDelPozo(jugador, 1);
                } else if (evento == EventoMazoPozo.ROBO_MAZO) {
                    return roboDelMazo(jugador,1);
                }
            } else {
                //TODO pedir cartas a bajar al controlador que seleccione cualquiera de los juegos a bajar
                this.juegosEnMesa.add(jugador.bajarJuego(jugador.getMano())); // primer juego
                this.juegosEnMesa.add(jugador.bajarJuego(jugador.getMano())); // segundo juego
                if (rondaActual.getNroRonda() >= 4){
                    this.juegosEnMesa.add(jugador.bajarJuego(jugador.getMano())); // tercer juego
                }
                return EventoMazoPozo.FINTURNO;
            }
        } else {
            // TODO le pido al controlador que me de la posicion de la carta a descartar(hiria le scanner)
            if (evento == EventoMazoPozo.ROBO_MAZO){
                return roboDelMazo(jugador, 1);
            }
            if (evento == EventoMazoPozo.UBICARCARTA){
                Juego juegoAUbicar = this.juegosEnMesa.getFirst();
                jugador.ubicar(1,juegoAUbicar);
                return EventoMazoPozo.FINTURNO;
            }
            if (evento == EventoMazoPozo.ROBO_POZO){
                //si roba en su turno no hay problema
                return roboDelPozo(jugador,1);
            }
            // lo que puede hacer si ya bajo todos los juegos(robar y ubicar)
        }
        if (jugador.getMano().isEmpty()){
            //TODO hacer un metodo que permita a cada jugador ubicar sus cartas, y luego de eso llame a la funcion contar puntos
            ArrayList<Integer> puntos = finRonda();
            //mostrar todo lo de puntos
            return EventoMazoPozo.FINRONDA;
            //TODO debo poder darle tiempo a los jugadores para que cada uno ubique sus cartas
            //se llama al metodo fin ronda que calcula los puntos de cada jugador y los suma a cada uno // TODO hacer que se muestre primero la lista de puntos y luego sume
        }
        this.jugadores.add(jugador);
        return EventoMazoPozo.FINTURNO;

    }

    public void robarFueraDeTurno(Jugador jugador){
        //si algun jugador no esta en turno, y desea robar, invoca este metodo, el cual en base a su situacion actual en la partida decide si puede interrumpiro no
        if (!jugador.isJuegoBajado()){
            jugador.setInterrumpe(true);
        }
        else{
            int posicion = new ArrayList<>(this.jugadores).indexOf(jugador);
            if (posicion == 1){ // me fijo que el jugador sea el siguiente que va a tener el turno
                jugador.setInterrumpe(true);
            }
        }
    }

    private void juegosBajados(EventoMazoPozo evento,Jugador jugador,int cartaDescarte){
        switch (evento){
            case ROBO_MAZO -> roboDelMazo(jugador,cartaDescarte);
            case UBICARCARTA -> ubicaCarta(jugador,juego,cartaDescarte);
            case ROBO_POZO -> roboDelPozo(jugador,cartaDescarte);
        }
    }

    private void ubicaCarta(Jugador jugador, Juego juego, int pos) {
        jugador.ubicar(pos,juego); // le paso la posicion de la carta a ubicar y el juego en donde ubicarlo
        //TODO va a notificar para que la vista muestre
    }

    private EventoMazoPozo roboDelPozo(Jugador jugador, int pos){
        jugador.robar(this.pozo);
        jugador.descartar(pos, this.pozo);
        jugadores.add(jugador);
        return EventoMazoPozo.FINTURNO;
    }

    private EventoMazoPozo roboDelMazo(Jugador jugador,int pos){
        jugador.robar(this.mazo);
        interrumpir(this.jugadores);
        jugador.descartar(pos, this.pozo);
        jugadores.add(jugador);
        return EventoMazoPozo.FINTURNO;
    }

    private void interrumpir(Queue<Jugador> jugadores){
        //me fijo ordenadamente si algun jugador quiere robar el pozo
        boolean centinela = true;
        for (Jugador jugador : jugadores) {
            if (jugador.isInterrumpe() && centinela) {
                jugador.robarFueraDeTurno(this.mazo,this.pozo);
                centinela = false;
            }
            jugador.setInterrumpe(false);
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


}
