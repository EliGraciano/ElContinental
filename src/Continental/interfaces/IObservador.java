package Continental.interfaces;
import Continental.modelo.eventos.Evento;

public interface IObservador {

    public void update(Evento evento);
}