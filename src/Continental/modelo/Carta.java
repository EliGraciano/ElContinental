package Continental.modelo;

import java.util.Objects;

public class Carta {
    private int valor;
    private Palo palo;

    public Carta(int valor, Palo palo) {
        this.valor = valor;
        this.palo = palo;
    }

    public int getValor() {
        return valor;
    }

    public Palo getPalo() {
        return palo;
    }

    public boolean isPalo(Carta carta){
        return this.getPalo() == carta.getPalo();
    }

    public boolean isPalo(Palo palo){
        return this.getPalo() == palo;
    }

    public boolean isValor(Carta carta){
        return this.getValor() == carta.getValor();
    }
    public boolean isValor(int valor){
        return this.getValor() == valor;
    }

    public int getPuntos(int contPuntos){
        //cuanto valgo?
        //responsabilidad de la carta
        if (this.getValor() == 1){
            contPuntos += 20;
        }
        else if (this.getValor() == 11){
            contPuntos += 10;
        }
        else if (this.getValor() == 12){
            contPuntos += 10;
        }
        else if (this.getValor() == 13){
            contPuntos += 10;
        }
        else {
            contPuntos += this.getValor(); // le sumo el valor original de  la carta(si es un mono 50)
        }
        return contPuntos;
    }

    @Override
    public String toString() {
        return "Carta{" +valor + " de " + palo + '}';
    }

    @Override
    public boolean equals(Object o) {
        // equals para comparar con otras cartas
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Carta carta = (Carta) o;
        return valor == carta.valor && palo == carta.palo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(valor, palo);
    }
    
}
