package Continental.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Escalera  extends ConjuntoDeCartas implements Juego{

    public Escalera(ArrayList<Carta> cartas) {
        this.cartas = cartas;
    }

    private Palo paloEscalera(){
        for (Carta carta : this.cartas){
            if (carta.getPalo() != Palo.MONO ){
                return carta.getPalo();
            }
        }
        return null;

    }

    private int valorPrimeraCarta(){
        // if que si es un mono devolver ese valor +1, ya que no puede haber dos monos juntos, por ende la siguiente si o si sera una carta con valor
        if (this.cartas.getFirst().getPalo() != Palo.MONO) {
            return this.cartas.getFirst().getValor();
        } else {
            //(porque en 0 esta el primer elemento entonces en 1 ya se que no es un mono, y tiene valor)
            return this.cartas.get(1).getValor() - 1;
        }
    }

    private int valorUltimaCarta(){
        // if que si es un mono devolver ese valor -1, ya que no puede haber dos monos juntos, por ende la anterior si o si sera una carta con valor
        if (this.cartas.getFirst().getPalo() != Palo.MONO) {
            return this.cartas.getLast().getValor();
        } else {
            //agarro el ante ultimo elemento que se que no es un mono, y le sumo uno (lo que seria el ultimo elemento)
            return this.cartas.get(this.cartas.size()-1).getValor() + 1;
        }
    }

    // sobre carga para poder acomodar una carta en un juego dado
    public boolean acomodar(Carta carta) {
        // TODO puede pasar que me quieran cambiar un comodin de la escalera por la carta que vale dicho comodin hay q hacerlo todavia
        Palo paloEscalera = this.paloEscalera();
        if (this.valorUltimaCarta() < 13 && this.valorPrimeraCarta() > 1) {
            // si la carta que me pasan se puede ubicar al final o al principio, la ubico
            if (carta.getValor() == this.valorUltimaCarta() + 1 || carta.getValor() == this.valorPrimeraCarta() - 1) {
                this.cartas.add(carta);
                return true;
            }
            else {
                //aca iria el q puedan acomodar
                return false;
            }
        }
        else {
            return false;
        }
    }




    }


