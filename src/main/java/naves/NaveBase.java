package naves;

import motor.Motor;
import motor.State;
import tripulacion.*;

import java.util.ArrayList;

public abstract class NaveBase {

    private int combustible;
    private int energia;
    private int desgaste;
    private Motor motor;
    private ArrayList<Tripulante> tripulacion;

    public NaveBase(int combustible, int energia) {
        this.combustible = combustible;
        this.energia = energia;
        this.desgaste = 0;
        this.motor = new Motor();
        this.tripulacion =new ArrayList<>();
    }

    public int getCombustible() {
        return combustible;
    }

    public int getEnergia() {
        return energia;
    }

    public int getDesgaste() {
        return desgaste;
    }

    public Motor getMotor() {
        return motor;
    }

    public void setCombustible(int combustible) {
        this.combustible = combustible;
    }

    public void setEnergia(int energia) {
        this.energia = energia;
    }

    public void setDesgaste(int desgaste) {
        this.desgaste = desgaste;
    }

    public void agregarTripulante(Tripulante t){
        this.tripulacion.add(t);
    }
    public void quitarTripulante(Tripulante t){
        this.tripulacion.remove(t);
    }
    public ArrayList<Tripulante> getTripulacion(){
        return this.tripulacion;
    }
    public String toString(){
        return "Combustible:"+this.combustible+"  Energia:"+this.energia+"  Desgaste:"+this.desgaste;
    }
    public void iniciarSalto(){
        motor.iniciarSalto();
    }
    public void cancelarSalto(){
        motor.cancelarSalto();
    }
    public void terminarWarp(){
        motor.terminarWarp();
    }
    public void completarEnfriamiento(){
        motor.completarEnfriamiento();
    }
    public void completarPreparacion(){
        motor.completarPreparacion();
    }
}