package Continental.controlador;

import Continental.modelo.Evento;
import Continental.modelo.Mesa;
import Continental.utilidades.IObservador;

public class Controlador implements IObservador {

    private Mesa mesa;

    public Controlador(Mesa mesa) {
        this.mesa = mesa;
    }


    @Override
    public void update(Evento evento) {

    }

}
