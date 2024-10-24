package Continental.modelo;

import java.util.ArrayList;

public interface ICombinacion {

    // metodo que todas las combinaciones deben implementar para saber si son validas
    boolean esValida(ArrayList<Carta> cartas);

}
