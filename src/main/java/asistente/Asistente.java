package asistente;

import destinoytablero.Destino;
import destinoytablero.Observable;
import destinoytablero.Observador;
import destinoytablero.Posicion;
import destinoytablero.Tablero;
import elementos.ElementoDelUniverso;
import misiones.Informe;
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
    private static final int CAPACIDAD_MAXIMA = 100;
    private static final int COSTO_SALTO_COMBUSTIBLE = 2;   // salto normal (anexo)
    private static final int COSTO_WARP_ENERGIA = 5;        // salto warp (anexo)
    private static final int DESGASTE_WARP = 3;             // enfriamiento posterior (anexo)

    private final NaveBase nave;
    private final Bitacora bitacora;
    private final List<Informe> informes = new ArrayList<>();
    private final Tablero tablero;      // null si el asistente se usa sin universo (Parte 1)
    private final Destino destino;      // turnos del tablero; null si no hay tablero
    private final Posicion base;        // posicion de la nave al crearse: la base de origen

    /** Asistente sin universo: sirve para recursos y bitacora, pero no para misiones. */
    public Asistente(NaveBase nave) {
        this(nave, null);
    }

    /**
     * Pre:  nave != null
     * Post: si tablero != null, la base de origen es la posicion actual de la nave en el tablero
     */
    public Asistente(NaveBase nave, Tablero tablero) {
        this.nave = Objects.requireNonNull(nave, "La nave no puede ser null");
        this.bitacora = new Bitacora();
        this.tablero = tablero;
        this.destino = (tablero == null) ? null : new Destino(tablero);
        this.base = (tablero == null) ? null : tablero.getPosicionNave();
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

    public int getGemas() {
        return this.nave.getGemas();
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

    // ------------------------------------------------------------------
    // Operaciones para las misiones: recursos, radares y movimiento
    // ------------------------------------------------------------------

    public boolean tieneTablero() {
        return tablero != null;
    }

    /** Pre: tieneTablero() */
    public Posicion getPosicion() {
        exigirTablero();
        return tablero.getPosicionNave();
    }

    /** Pre: tieneTablero(). Post: posicion de la nave cuando se creo el asistente. */
    public Posicion getBase() {
        exigirTablero();
        return base;
    }

    /** Pre: tieneTablero(). Post: sortea un destino libre en el tablero (lo notifica a los observadores). */
    public Posicion obtenerObjetivo() {
        exigirTablero();
        return tablero.obtenerObjetivo();
    }

    /**
     * Radar de largo alcance.
     * Pre:  tieneTablero() && destino != null
     * Post: camino desde la posicion actual hasta destino (ambos incluidos); vacio si no hay
     */
    public List<Posicion> calcularRuta(Posicion destino) {
        exigirTablero();
        Objects.requireNonNull(destino, "Falta el destino");
        return Radar.calcularRuta(tablero, tablero.getPosicionNave(), destino);
    }

    /**
     * Radar de corto alcance.
     * Pre:  tieneTablero()
     * Post: true si y solo si p es un casillero vecino de la nave con un obstaculo
     */
    public boolean hayObstaculoEn(Posicion p) {
        exigirTablero();
        Objects.requireNonNull(p, "Falta la posicion");
        return Radar.hayObstaculoEn(tablero, p);
    }

    /** Turno del tablero: Destino mueve al azar los elementos. Pre: tieneTablero() */
    public void avanzarTablero() {
        exigirTablero();
        destino.turnoDestino();
    }

    /**
     * Cambio de recursos de todo o nada: o se aplican las cuatro variaciones o no cambia nada.
     * Post: si devuelve true, combustible, energia y desgaste siguen en [0, 100] y gemas >= 0;
     *       si el resultado deja la nave sin condiciones de vida, queda destruida.
     * Post: si devuelve false, la nave no cambio y se registra el rechazo.
     */
    public boolean aplicarVariacion(int dCombustible, int dEnergia, int dDesgaste, int dGemas) {
        int combustible = nave.getCombustible() + dCombustible;
        int energia = nave.getEnergia() + dEnergia;
        int desgaste = nave.getDesgaste() + dDesgaste;
        int gemas = nave.getGemas() + dGemas;
        if (combustible < 0 || combustible > CAPACIDAD_MAXIMA
                || energia < 0 || energia > CAPACIDAD_MAXIMA
                || desgaste < 0 || desgaste > CAPACIDAD_MAXIMA
                || gemas < 0) {
            registrar("Cambio de recursos rechazado: dejaria un recurso fuera de rango");
            return false;
        }
        nave.setCombustible(combustible);
        nave.setEnergia(energia);
        nave.setDesgaste(desgaste);
        nave.setGemas(gemas);
        evaluarDestruccion();
        notificarObservadores();
        return true;
    }

    /**
     * Salto normal: avanza un casillero y consume 2 de combustible.
     * Pre:  tieneTablero() && siguiente es vecino inmediato de la nave y esta dentro del tablero
     * Post: true si la nave se movio; false si no pudo (destruida o sin combustible para el salto)
     * NOTA: provisorio. Cuando exista el Motor de la Parte 2, esto delega en motor.saltar().
     */
    public boolean saltar(Posicion siguiente) {
        exigirTablero();
        Objects.requireNonNull(siguiente, "Falta la posicion");
        Posicion actual = tablero.getPosicionNave();
        int distancia = Math.max(Math.abs(siguiente.getX() - actual.getX()),
                Math.abs(siguiente.getY() - actual.getY()));
        if (distancia != 1 || !Radar.dentro(tablero, siguiente)) {
            throw new IllegalArgumentException("El salto normal avanza un casillero dentro del tablero");
        }
        if (!naveViva() || nave.getCombustible() < COSTO_SALTO_COMBUSTIBLE) {
            return false;
        }
        aplicarVariacion(-COSTO_SALTO_COMBUSTIBLE, 0, 0, 0);
        if (!naveViva()) {
            return false;
        }
        tablero.moverNave(siguiente);
        return true;
    }

    /**
     * Salto warp: avanza dos casilleros en la direccion de siguiente, esquivando lo que haya en medio.
     * Consume 5 de energia y genera 3 de desgaste.
     * Pre:  tieneTablero() && siguiente es vecino inmediato de la nave
     * Post: true si la nave se movio; false si no pudo (motor no disponible, sin energia,
     *       desgaste que pasaria de 100 o aterrizaje fuera del tablero) y entonces no cambia nada
     * NOTA: provisorio. Faltan los 2 turnos de preparacion y el de enfriamiento: cuando exista
     *       el Motor de la Parte 2 (State), esto delega en motor.saltarWarp().
     */
    public boolean saltarWarp(Posicion siguiente) {
        exigirTablero();
        Objects.requireNonNull(siguiente, "Falta la posicion");
        Posicion actual = tablero.getPosicionNave();
        int dx = Integer.signum(siguiente.getX() - actual.getX());
        int dy = Integer.signum(siguiente.getY() - actual.getY());
        if (dx == 0 && dy == 0) {
            throw new IllegalArgumentException("El salto warp necesita una direccion");
        }
        Posicion aterrizaje = new Posicion(actual.getX() + 2 * dx, actual.getY() + 2 * dy);
        if (!naveViva() || !naveDisponible() || !Radar.dentro(tablero, aterrizaje)
                || nave.getEnergia() < COSTO_WARP_ENERGIA
                || nave.getDesgaste() + DESGASTE_WARP > CAPACIDAD_MAXIMA) {
            return false;
        }
        aplicarVariacion(0, -COSTO_WARP_ENERGIA, DESGASTE_WARP, 0);
        if (!naveViva()) {
            return false;
        }
        tablero.moverNave(aterrizaje);
        registrar("Salto warp de (" + actual.getX() + ", " + actual.getY() + ") a ("
                + aterrizaje.getX() + ", " + aterrizaje.getY() + ")");
        return true;
    }

    private void exigirTablero() {
        if (tablero == null) {
            throw new IllegalStateException("El asistente no tiene un tablero asociado");
        }
    }
}