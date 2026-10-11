package misiones;
import asistente.Asistente;

public class FactoryMision {

    /**
     * Pre:  a != null
     * Post: devuelve Mision01, Mision02 o Mision03 segun numeroMision (1, 2 o 3)
     *       devuelve null si numeroMision no esta en {1, 2, 3}
     */
    public static Mision crearMision(int numeroMision,Asistente a){
        if (numeroMision == 1){return new Mision01(a);}
        if (numeroMision == 2){return new Mision02(a);}
        if (numeroMision == 3){return new Mision03(a);}
        return null;
    }
}
