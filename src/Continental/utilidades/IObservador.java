package Continental.utilidades;

import Continental.modelo.EventoMazoPozo;

public interface IObservador {

    public void notificar(EventoMazoPozo eventoMazoPozo);
}
