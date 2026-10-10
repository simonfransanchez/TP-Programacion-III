package naves;

import motor.Motor;

public interface Asistente {

int getCombustible();

int getEnergia();

int getDesgaste();

Motor getMotor();

void cargarCombustible(int cantidad);

void cargarEnergia(int cantidad);

boolean requiereMantenimiento();

void realizarMantenimiento();
}