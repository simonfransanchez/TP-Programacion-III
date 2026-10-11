package asistente;

import misiones.Mision;
import motor.Motor;
import motor.State;
import naves.NaveBase;

import java.util.ArrayList;

public class Asistente {
    private NaveBase nave;
    private ArrayList<String> bitacora;
    private Mision mision;

    public Asistente(NaveBase nave) {
        this.nave = nave;
    }

    public int getCombustible() {
        return this.nave.getCombustible();
    }

    public int getEnergia(){
        return this.nave.getEnergia();
    }

    public int getDesgaste() {
        return this.nave.getDesgaste();
    }

    public Motor getMotor(){
        return this.nave.getMotor();
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

    public void comenzarMision()

}