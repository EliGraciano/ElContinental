package Continental.modelo.cartas;

import java.util.ArrayList;

public interface IValidador {

    // metodo que todos los validadores implementan para saber si una combinacion es valida
    boolean esValida(ArrayList<Carta> cartas);
//TODO poner conjuntodecatas como tipo
}
