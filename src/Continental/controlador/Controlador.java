package Continental.controlador;

import Continental.modelo.Evento;
import Continental.modelo.IMesa;
import Continental.utilidades.IObservador;
import ar.edu.unlu.rmimvc.cliente.IControladorRemoto;
import ar.edu.unlu.rmimvc.observer.IObservableRemoto;

import java.rmi.RemoteException;

public class Controlador implements IControladorRemoto {

    private IMesa IMesa;

    public Controlador(IMesa IMesa) {
        this.IMesa = IMesa;
    }

    public void setModeloRemoto(){

    }

    @Override
    public <T extends IObservableRemoto> void setModeloRemoto(T t) throws RemoteException {

    }

    @Override
    public void actualizar(IObservableRemoto iObservableRemoto, Object o) throws RemoteException {

    }
}
