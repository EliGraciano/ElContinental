package Continental.utilidades;

import Continental.modelo.EventoMazoPozo;

public interface IObservable {

    public void agregarObservador(IObservador observador);

    public void eliminarObservador(IObservador observador);

    public void notificar(EventoMazoPozo eventoMazoPozo);

}
