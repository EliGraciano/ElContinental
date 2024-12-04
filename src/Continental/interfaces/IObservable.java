package Continental.interfaces;

import Continental.modelo.eventos.Evento;

public interface IObservable {

    public void agregarObservador(IObservador observador) throws  Exception ;

    public void eliminarObservador(IObservador observador) throws  Exception ;

    public void notificar(Evento evento);

}
