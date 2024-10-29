package Continental.modelo;

public abstract class Ronda {
    //clase de herencia ya que va a haber 7 rondas
    //TODO falta hacer todas las rondas
    private int cartasADar;

    private Jugador jugadorInicial;

    private ConjuntoDeCartas combinaciones; // cambiar el tipo por un tipo de combinacion o algo por el estilo

    public abstract int getCartasADar();

}
