package asistente;

import destinoytablero.Observable;
import destinoytablero.Observador;
import destinoytablero.Tablero;
import elementos.ElementoDelUniverso;
import misiones.Informe;
import misiones.Mision;
import motor.Motor;
import motor.State;
import naves.NaveBase;
import bitacora.Bitacora;
import naves.ResultadoCombate;

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
    private static final int CAPACIDAD_MAXIMA = 100;
    private Tablero tablero;

    public Asistente(NaveBase nave) {
        this.nave = Objects.requireNonNull(nave, "La nave no puede ser null");   //corrrecion primera parte
        this.tablero = Objects.requireNonNull(tablero, "El tablero no puede ser null");
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

    /**
     * Post: true si y solo si la nave no fue destruida
     *       && desgaste < 100 && energia > 0 && combustible > 0
     */
    public boolean naveViva() {
        return motivoDeDestruccion() == null;
    }

    private String motivoDeDestruccion() {
        if (nave.estaDestruida())          return "la nave ya estaba destruida";
        if (nave.getDesgaste() >= 100)     return "el desgaste llegó a 100";
        if (nave.getEnergia() <= 0)        return "la energía llegó a 0";
        if (nave.getCombustible() <= 0)    return "el combustible llegó a 0";
        return null;
    }

    /**
     * Pre:  motivo != null
     * Post: nave.estaDestruida() == true; es idempotente (una segunda llamada no hace nada)
     * Post: se registra la destrucción una sola vez
     */
    public void destruirNave(String motivo) {
        Objects.requireNonNull(motivo, "Falta el motivo");
        if (nave.estaDestruida()) return;
        nave.destruir();
        registrar("NAVE DESTRUIDA: " + motivo + ". La tripulación perece.");
        notificarObservadores();
    }

    /** Destruye la nave si dejó de cumplir las condiciones de vida (llamarlo tras cualquier cambio de recursos). */
    private void evaluarDestruccion() {
        String motivo = motivoDeDestruccion();
        if (motivo != null && !nave.estaDestruida()) {
            destruirNave(motivo);
        }
    }

    /**
     * Pre:  cantidad >= 0
     * Post: getEnergia() == max(0, energia_old - cantidad); nunca se rechaza
     *       (un impacto no se puede negar, a diferencia de un consumo voluntario)
     * Post: si la energía llega a 0, la nave queda destruida
     */
    public void perderEnergia(int cantidad) {
        if (cantidad < 0) throw new IllegalArgumentException("La pérdida no puede ser negativa");
        nave.setEnergia(Math.max(0, nave.getEnergia() - cantidad));
        evaluarDestruccion();
        notificarObservadores();
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

    /**
     * Pre:  combustible >= 0 && energia >= 0
     * Post: si naveViva(): cada recurso sube hasta un máximo de 100 (se recorta, no se rechaza)
     *       si no: no cambia nada (una nave destruida no se recarga)
     */
    public void recargar(int combustible, int energia) {
        if (combustible < 0 || energia < 0) throw new IllegalArgumentException("Las recargas no pueden ser negativas");
        if (!naveViva()) return;
        nave.setCombustible(Math.min(CAPACIDAD_MAXIMA, nave.getCombustible() + combustible));
        nave.setEnergia(Math.min(CAPACIDAD_MAXIMA, nave.getEnergia() + energia));
        notificarObservadores();
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

    public ResultadoCombate combatirContra(NaveBase enemiga) {
        Objects.requireNonNull(enemiga, "La nave enemiga no puede ser null");
        return nave.combatirContra(enemiga);
    }

    /**
     * Pre:  el asistente fue creado con un tablero
     * Post: si naveViva() y hay un elemento en la posición de la nave, ese elemento interactúa con la nave
     *       si no hay elemento (o la nave está destruida), no hace nada
     * Post: si la interacción dejó a la nave sin condiciones de vida, queda destruida
     */
    public void interactuarConElementoActual() {
        if (tablero == null) throw new IllegalStateException("El asistente no tiene un tablero asociado");
        if (!naveViva()) return;
        ElementoDelUniverso elemento = tablero.inspeccionarPosicion();
        if (elemento != null) {
            elemento.interactuar(this);
            evaluarDestruccion();
            notificarObservadores();
        }
    }

}