package naves;

public class NaveCombate extends NaveBase {

    public NaveCombate() {
        super(80, 100);
    }
    public String toString(){
        return super.toString()+"  Tipo: Combate";
    }
}