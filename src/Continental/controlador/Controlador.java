package Continental.controlador;

import Continental.modelo.Evento;
import Continental.modelo.IMesa;
import Continental.modelo.Mesa;
import Continental.utilidades.IObservador;
import ar.edu.unlu.rmimvc.cliente.IControladorRemoto;
import ar.edu.unlu.rmimvc.observer.IObservableRemoto;

import java.rmi.RemoteException;

public class Controlador implements IObservador {

    private Mesa mesa;

    public Controlador(IMesa IMesa) {
        this.mesa = mesa;
    }



    @Override
    public void update(Evento evento) {

    }
}
