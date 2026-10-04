package naves;

public class NaveExploradora implements Nave {

    private int combustible;
    private int energia;
    private int desgaste;

    public NaveExploradora() {
        combustible = 60;
        energia = 80;
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