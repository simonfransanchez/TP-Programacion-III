package asistente;

import destinoytablero.Observable;
import destinoytablero.Observador;
import misiones.Informe;
import misiones.Mision;
import motor.Motor;
import motor.State;
import naves.NaveBase;
import bitacora.Bitacora;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Inv: nave != null && bitacora != null
 */
public class Asistente extends Observable {
    private NaveBase nave;
    private Bitacora bitacora;
    private final List<Informe> informes = new ArrayList<>();

    public Asistente(NaveBase nave) {
        this.nave = Objects.requireNonNull(nave, "La nave no puede ser null");   //corrrecion primera parte
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

    /**
     * Pre:  cantidad > 0
     * Post: si combustible_old + cantidad <= 100:
     *           getCombustible() == combustible_old + cantidad y se registra "Se cargo nafta"
     *       si no: getCombustible() == combustible_old y se registra el rechazo
     */
    public void cargarCombustible(int cantidad) {
        int combustible = nave.getCombustible();
        if (combustible + cantidad <= 100) {
            nave.setCombustible(combustible += cantidad);
            bitacora.agregarEvento("Se cargo nafta "+cantidad);
            notificarObservadores();
        }
        else{
            bitacora.agregarEvento("El combustible no entraba en el tanque");
        }
    }

    /**
     * Post: devuelve true si y solo si getDesgaste() >= 80
     */
    public boolean requiereMantenimiento() {
        return nave.getDesgaste() >= 80;
    }

    /**
     * Post: devuelve true si y solo si el motor esta en estado Disponible
     */
    public boolean naveDisponible(){
        Motor motor = nave.getMotor();
        State estado = motor.getEstado();
        return (estado.estaDisponible());
    }

    /**
     * Post: getDesgaste() == 0 y se registra el mantenimiento en la bitacora
     */
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
            notificarObservadores();
        }
        else{
            bitacora.agregarEvento("La energia no entraba en el tanque");
        }

    }

    public void setCombustible(int i){
        this.nave.setCombustible(i);
    }

    public void agregarInformeBitacora(Informe i) {
        synchronized (this) { informes.add(i); }
        bitacora.agregarEvento(i.toString());
        notificarObservadores();
    }

    public void mostrarBitacora(){
        bitacora.mostrarBitacora();
    }

    public void agregarObservadorBitacora(Observador o) {
        bitacora.agregarObservador(o);
    }

    public List<String> getEventos() {
        return bitacora.getEventos();
    }

    public synchronized List<Informe> getInformes() {
        return new ArrayList<>(informes);
    }
}