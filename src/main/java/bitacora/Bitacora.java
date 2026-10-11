package bitacora;

import java.util.ArrayList;

public class Bitacora {
    private ArrayList<String> eventos;
    private int numero;

    public Bitacora() {
        this.eventos=new ArrayList<>();
        this.numero=0;
    }
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
