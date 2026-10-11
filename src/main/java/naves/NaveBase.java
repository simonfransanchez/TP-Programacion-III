package naves;

import motor.Motor;
import motor.State;
import tripulacion.*;

import java.util.ArrayList;

/**
 * Inv: 0 <= combustible <= 100 && 0 <= energia <= 100 && 0 <= desgaste <= 100
 * Inv: motor != null && tripulacion != null
 */
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

    /**
     * Pre:  t != null
     * Post: tripulacion contiene a t
     */
    public void agregarTripulante(Tripulante t){
        this.tripulacion.add(t);
    }

    /**
     * Post: tripulacion no contiene a t
     */
    public void quitarTripulante(Tripulante t){
        this.tripulacion.remove(t);
    }
    public ArrayList<Tripulante> getTripulacion(){
        return this.tripulacion;
    }
    public String toString(){
        return "Combustible:"+this.combustible+"  Energia:"+this.energia+"  Desgaste:"+this.desgaste;
    }

    /**
     * Post: el estado del motor cambia solo si la transicion es valida
     *       desde el estado actual (ver State)
     */
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