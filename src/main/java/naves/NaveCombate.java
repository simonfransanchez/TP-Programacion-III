package naves;

public class NaveCombate implements Nave {

    private int combustible;
    private int energia;
    private int desgaste;

    public NaveCombate() {
        combustible = 80;
        energia = 100;
        desgaste = 0;
    }

    @Override
    public int getCombustible() {
        return combustible;
    }

    @Override
    public int getEnergia() {
        return energia;
    }

    @Override
    public int getDesgaste() {
        return desgaste;
    }

}