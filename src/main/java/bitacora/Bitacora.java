package bitacora;

import java.util.ArrayList;

/**
 * Inv: los eventos estan en orden de insercion y numerados consecutivamente desde 1
 */
public class Bitacora {
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
    public void agregarEvento(String s){
        numero++;
        this.eventos.addLast(numero +". "+ s);
    }

    public void mostrarBitacora(){
        for (String evento : this.eventos) {
            System.out.println(evento);
        }
    }

}
