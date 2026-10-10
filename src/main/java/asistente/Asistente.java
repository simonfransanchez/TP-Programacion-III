package asistente;

import motor.Motor;
import motor.State;
import naves.NaveBase;

import java.util.ArrayList;

public class Asistente {
    private NaveBase nave;
    private ArrayList<String> bitacora;

    public Asistente(NaveBase nave) {
        this.nave = nave;
    }

    public int getCombustible() {
        return this.nave.getCombustible();
    }

    public int getEnergia(){
        return nave.getEnergia();
    }

    public int getDesgaste() {
        return nave.getDesgaste();
    }

    public Motor getMotor(){
        return nave.getMotor();
    }

    public void cargarCombustible(int cantidad) {
        int combustible = nave.getCombustible();
        if (combustible + cantidad <= 100) {
            nave.setCombustible(combustible += cantidad);
        }
    }

    public boolean requiereMantenimiento() {
        return nave.getDesgaste() >= 80;
    }

    public boolean naveDisponible(){
        Motor motor = nave.getMotor();
        State estado = motor.getEstado();
        return (estado.estaDisponible());
    }

    public void realizarMantenimiento() {
        nave.setDesgaste(0);
    }

    public void registrar(String registro){
        this.bitacora.add(registro);
    }

    public void cargarEnergia(int cantidad) {
        int energia = nave.getEnergia();
        if (energia + cantidad <= 100) {
            nave.setEnergia(energia += cantidad);
        }
    }
}