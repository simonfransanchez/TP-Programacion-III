package asistente;

import misiones.Informe;
import misiones.Mision;
import motor.Motor;
import motor.State;
import naves.NaveBase;
import bitacora.Bitacora;

public class Asistente {
    private NaveBase nave;
    private Bitacora bitacora;

    public Asistente(NaveBase nave) {
        this.nave = nave;
        this.bitacora = new Bitacora();
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
            bitacora.agregarEvento("Se cargo nafta "+cantidad);
        }
        else{
            bitacora.agregarEvento("El combustible no entraba en el tanque");
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
        bitacora.agregarEvento("Se realizo mantenimiento");
    }

    public void registrar(String registro){
        this.bitacora.agregarEvento(registro);
    }

    public void cargarEnergia(int cantidad) {
        int energia = nave.getEnergia();
        if (energia + cantidad <= 100) {
            nave.setEnergia(energia += cantidad);
            bitacora.agregarEvento("Se cargo energia "+cantidad);
        }
        else{
            bitacora.agregarEvento("La energia no entraba en el tanque");
        }
    }
    public void setCombustible(int i){
        this.nave.setCombustible(i);
    }
    public void agregarInformeBitacora(Informe i){
        bitacora.agregarEvento(i.toString());
    }
    public void mostrarBitacora(){
        bitacora.mostrarBitacora();
    }

}