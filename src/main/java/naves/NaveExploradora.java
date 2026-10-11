package naves;

public class NaveExploradora extends NaveBase {

    public NaveExploradora() {
        super(60, 80);
    }
    public String toString(){
        return super.toString()+"  Tipo: Exploradora";
    }
}