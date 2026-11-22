package naves;

import motor.Motor;
import tripulacion.*;

import java.util.ArrayList;

/**
 * Inv: 0 <= combustible <= 100 && 0 <= energia <= 100 && 0 <= desgaste <= 100
 * Inv: gemas >= 0
 * Inv: motor != null && tripulacion != null
 */
public abstract class NaveBase {
    private boolean destruida = false;
    private int combustible;
    private int energia;
    private int desgaste;
    private int gemas;
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

    public int getGemas() {
        return gemas;
    }

    public void setGemas(int gemas) {
        this.gemas = gemas;
    }

    public boolean estaDestruida() { return destruida; }

    public void destruir() { destruida = true; }

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
        return "Combustible:"+this.combustible+"  Energia:"+this.energia+"  Desgaste:"+this.desgaste+"  Gemas:"+this.gemas;
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

    public abstract ResultadoCombate combatirContra(NaveBase enemiga);   // 1.er despacho: receptor = nave propia
    public abstract ResultadoCombate resultadoParaExploradora();         // 2.º despacho: receptor = enemiga
    public abstract ResultadoCombate resultadoParaCarguero();
    public abstract ResultadoCombate resultadoParaCombate();
}