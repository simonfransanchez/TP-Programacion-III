package naves;

public class NaveCarguero extends NaveBase {

    public NaveCarguero() {
        super(100, 60);
    }
    public String toString(){
        return super.toString()+"  Tipo: Carguero";
    }
}