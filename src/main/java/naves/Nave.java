package naves;

import motor.Motor;

public interface Nave {

    int getCombustible();

    int getEnergia();

    int getDesgaste();

    Motor getMotor();

    void cargarCombustible(int cantidad);

    void cargarEnergia(int cantidad);

    boolean requiereMantenimiento();

    void realizarMantenimiento();
}