package Continental.modelo;

public interface IMesa {
    // interfaz en el que van todas las funciones con las uqe podra interactuar el controlador

    public void iniciarRonda();

    public void repartir(Ronda ronda);

    public EventoMazoPozo jugarTurno(EventoMazoPozo evento); //

    public void robarFueraDeTurno(Jugador jugador); // pone el interrumpe en true




}
