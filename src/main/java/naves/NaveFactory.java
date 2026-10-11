package naves;

public class NaveFactory {

    public static NaveBase getNave(String tipo){
        if (tipo == null)
            return null;
        if (tipo.equalsIgnoreCase("Carguero"))
            return new NaveCarguero();
        else if (tipo.equals("Exploradora"))
            return new NaveExploradora();
        else if (tipo.equals("Combate"))
            return new NaveCombate();

        return null;
    }
}
