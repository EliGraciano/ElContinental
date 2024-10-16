package Continental.utilidades;

public interface IObservable {

    public void agregarObservador(IObservador observador);

    public void notificar(Evento libroAgregado);

}
