package Continental.modelo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Mesa {
    //donde transcurrira el juego?, ronda o en mesa?

    Mazo mazo;

    Pozo pozo;

    Queue<Jugador> jugadores;

    ArrayList<Juego> juegosEnMesa;

    // que reciba un numero (cantidad de jugadores) y en base a este decida cuantos mazos crear
    public Mesa(int cantJugadores) {
        // TODO si la cantidad de jugadores es igual o menor a 4, poder usar grafica, si es mayor usar vista consola
        this.pozo = new Pozo();
        this.juegosEnMesa = new ArrayList<>();
        if (cantJugadores < 5){
            this.mazo = new Mazo(2);
            this.mazo.mezclar();
            this.jugadores = crearJugadores(cantJugadores);
        }
        else if (cantJugadores > 5 && cantJugadores < 8){
            this.mazo = new Mazo(3);
            this.mazo.mezclar();
            // como hago que me pasen el nombre desde la vista?
            this.jugadores = crearJugadores(cantJugadores);
        }
        else {throw new RuntimeException();}
    }

    private Queue<Jugador> crearJugadores(int cantJugadores){
        Queue<Jugador> jugadoresCreados = new LinkedList<>();
        for (int i = 0; i < cantJugadores; i++){
            jugadoresCreados.add(altaJugador("Pasar nombre aca"));
        }
        return jugadoresCreados;
    }

    private Jugador altaJugador(String nombre){
        return new Jugador(nombre);
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
                if (evento == EventoMazoPozo.ROBO_POZO) {
                    jugador.robar(this.pozo);
                    // TODO le pido al controlador que me de la posicion de la carta a descartar(hiria le scanner)
                    jugador.descartar(1, this.pozo);
                    jugadores.add(jugador);
                    return EventoMazoPozo.FINTURNO;
                } else if (evento == EventoMazoPozo.ROBO_MAZO) {
                    jugador.robar(this.mazo);
                    interrumpir(this.jugadores);
                    // TODO le pido al controlador que me de la posicion de la carta a descartar(hiria le scanner)
                    jugador.descartar(1, this.pozo);
                    jugadores.add(jugador);
                    return EventoMazoPozo.FINTURNO;
                }
            } else {
                //TODO pedir cartas a bajar al controlador que seleccione cualquiera de los juegos a bajar

                this.juegosEnMesa.add(jugador.bajarJuego());
                return EventoMazoPozo.FINTURNO;

                //TODO como implementar los juegos en la mesa?

            }
        } else {
            if (evento == EventoMazoPozo.ROBO_MAZO){
                jugador.robar(this.mazo);
                interrumpir(this.jugadores);
                // TODO le pido al controlador que me de la posicion de la carta a descartar(hiria le scanner)
                jugador.descartar(1, this.pozo);
                jugadores.add(jugador);
                return EventoMazoPozo.FINTURNO;
            }
            if (evento == EventoMazoPozo.UBICARCARTA){
                // TODO le pido al controlador que me de la posicion de la carta a ubicar(hiria el scanner),y el juego al que quiere ubicarla
                Juego juegoAUbicar = this.juegosEnMesa.getFirst();
                jugador.ubicar(1,juegoAUbicar);
                return EventoMazoPozo.FINTURNO;

            }
            if (evento == EventoMazoPozo.ROBO_POZO){
                // solo puede robar del pozo, cuando sea el turno del jugador anterior a el
                return EventoMazoPozo.FINTURNO;
            }

            // lo que puede hacer si ya bajo todos los juegos(robar y ubicar)
        }
        if (jugador.getMano().isEmpty()){
            finRonda();
            return EventoMazoPozo.FINRONDA;
            //se llama al metodo fin ronda que calcula los puntos de cada jugador y los suma a cada uno
        }

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

    private void finRonda(){
        //recorro todos lso jugadores y sumo los puntos de las cartas que tienen en la mano
        //usar el getPuntos para ver con cuantos puntos queda cada jugador
        for (Jugador jugador : this.jugadores){
            jugador.addPuntos();
        }
    }











}