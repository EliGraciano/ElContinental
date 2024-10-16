package Continental.utilidades;

import Continental.modelo.Evento;

public interface IObservable {

    public void agregarObservador(IObservador observador);

    public void eliminarObservador(IObservador observador);

    public void notificar(Evento evento);

}
