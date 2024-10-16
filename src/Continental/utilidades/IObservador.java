package Continental.utilidades;

import Continental.modelo.Evento;

public interface IObservador {

    public void notificar(Evento evento);
}
