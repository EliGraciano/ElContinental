package Continental.modelo;

import java.util.ArrayList;

public class Tercia implements ICombinacion{

    @Override
    public boolean esValida(ArrayList<Carta> cartas) {
        // Necesitamos exactamente tres cartas
        // TODO Hacer metodo de verificacion
        if (cartas.size() != 3) return false;
        return true;
    }
    
}
