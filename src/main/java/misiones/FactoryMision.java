package misiones;

public class FactoryMision {
    public static Mision crearMision(int numeroMision){
        if (numeroMision == 1){return new Mision01();}
        if (numeroMision == 2){return new Mision02();}
        if (numeroMision == 3){return new Mision03();}
        return null;
    }
}
