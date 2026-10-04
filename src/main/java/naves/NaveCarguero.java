package naves;

public class NaveCarguero implements Nave {

    private int combustible;
    private int energia;
    private int desgaste;

    public NaveCarguero() {
        combustible = 100;
        energia = 60;
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