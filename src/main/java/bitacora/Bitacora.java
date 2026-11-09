package bitacora;

import destinoytablero.Observable;

import java.util.ArrayList;
import java.util.List;

/**
 * Inv: los eventos estan en orden de insercion y numerados consecutivamente desde 1
 */
public class Bitacora extends Observable {
    private ArrayList<String> eventos;
    private int numero;

    public Bitacora() {
        this.eventos=new ArrayList<>();
        this.numero=0;
    }

    /**
     * Pre:  s != null
     * Post: eventos.size() == eventos_old.size() + 1
     * Post: el ultimo evento es numero + ". " + s
     */
    public void agregarEvento(String s) {
        synchronized (this) {
            numero++;
            eventos.add(numero + ". " + s);
        }
        notificarObservadores();
    }

    public void mostrarBitacora(){
        for (String evento : this.eventos) {
            System.out.println(evento);
        }
    }

    public synchronized List<String> getEventos() {
        return new ArrayList<>(eventos);
    }

}
