package misiones;
import asistente.Asistente;

public class FactoryMision {
    public static Mision crearMision(int numeroMision,Asistente a){
        if (numeroMision == 1){return new Mision01(a);}
        if (numeroMision == 2){return new Mision02(a);}
        if (numeroMision == 3){return new Mision03(a);}
        return null;
    }
}
