package naves;

import motor.Motor;

public abstract class NaveBase implements Nave {

    private int combustible;
    private int energia;
    private int desgaste;
    private Motor motor;

    public NaveBase(int combustible, int energia) {
        this.combustible = combustible;
        this.energia = energia;
        this.desgaste = 0;
        this.motor = new Motor();
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

    @Override
    public Motor getMotor() {
        return motor;
    }

    @Override
    public void cargarCombustible(int cantidad) {
        if (combustible + cantidad <= 100) {
            combustible += cantidad;
        }
    }

    @Override
    public void cargarEnergia(int cantidad) {
        if (energia + cantidad <= 100) {
            energia += cantidad;
        }
    }

    @Override
    public boolean requiereMantenimiento() {
        return desgaste >= 80;
    }

    @Override
    public void realizarMantenimiento() {
        desgaste = 0;
    }
}