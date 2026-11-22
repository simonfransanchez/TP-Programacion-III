package misiones;

import asistente.Asistente;
import destinoytablero.Posicion;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Template Method del ciclo de vida de una mision (Parte 2): preparar -> ejecutar -> evaluar -> cerrar.
 *
 * Inv: asistente != null && energiaAdicional >= 0
 * Inv: una instancia se realiza una sola vez (una mision es de un asistente y de una ejecucion)
 * Inv: la nave solo cambia por el asistente; el descuento del despegue ocurre en ejecutar
 *      y la entrega o recompensa final solo en cerrar
 */
public abstract class Mision {

    private static final int COMBUSTIBLE_DESPEGUE = 4;
    private static final int DESGASTE_DESPEGUE = 4;
    private static final int MAX_TURNOS = 200;      // proteccion contra un viaje que no termina

    private final Asistente asistente;
    private final int energiaAdicional;
    private int pausaMs = 300;
    private boolean realizada = false;

    // datos de la ejecucion
    private Posicion destino;
    private Posicion posicionInicial;
    private List<Posicion> ruta = new ArrayList<Posicion>();
    private final List<String> recorrido = new ArrayList<String>();
    private final List<String> acciones = new ArrayList<String>();
    private final List<String> eventosRelevantes = new ArrayList<String>();
    private final List<String> observaciones = new ArrayList<String>();
    private boolean exitosa = false;
    private Ajuste ajuste = Ajuste.NULO;
    private int combustibleInicial, energiaInicial, desgasteInicial;
    private Informe informe;

    /**
     * Variacion de recursos que se aplica al cerrar una mision exitosa (entrega, recompensa o recarga).
     * Los valores son relativos: negativo resta y positivo suma.
     */
    protected static final class Ajuste {
        static final Ajuste NULO = new Ajuste("", 0, 0, 0, 0);

        private final String descripcion;
        private final int combustible, energia, desgaste, gemas;

        public Ajuste(String descripcion, int combustible, int energia, int desgaste, int gemas) {
            this.descripcion = Objects.requireNonNull(descripcion);
            this.combustible = combustible;
            this.energia = energia;
            this.desgaste = desgaste;
            this.gemas = gemas;
        }
    }

    /**
     * Pre:  asistente != null && energiaAdicional >= 0
     * Post: la mision queda atada a ese asistente
     */
    protected Mision(Asistente asistente, int energiaAdicional) {
        this.asistente = Objects.requireNonNull(asistente, "Falta asignar un asistente para la mision");
        if (energiaAdicional < 0) {
            throw new IllegalArgumentException("La energia requerida no puede ser negativa");
        }
        this.energiaAdicional = energiaAdicional;
    }

    // ------------------------------------------------------------------
    // Metodo plantilla
    // ------------------------------------------------------------------

    /**
     * Pre:  la mision no fue realizada antes
     * Post: devuelve el informe (nunca null), tambien cuando la mision es rechazada o falla
     * Post: se ejecutaron en este orden: preparar, ejecutar (solo si preparar acepto), evaluar, cerrar
     * Post: si fue rechazada en preparar, la nave no cambio
     * Post: el informe queda en la bitacora y en el historial del asistente
     * @throws IllegalStateException si la mision ya fue realizada
     */
    public final Informe realizarMision() {
        if (realizada) {
            throw new IllegalStateException("La mision ya fue realizada: hay que crear una nueva");
        }
        realizada = true;
        combustibleInicial = asistente.getCombustible();
        energiaInicial = asistente.getEnergia();
        desgasteInicial = asistente.getDesgaste();
        asistente.registrar(nombreMision() + ": iniciada");

        if (preparar()) {
            ejecutar();
        }
        evaluar();
        cerrar();

        asistente.agregarInformeBitacora(informe);
        return informe;
    }

    // ------------------------------------------------------------------
    // Fase 1: preparar
    // ------------------------------------------------------------------

    /** Verifica recursos y calcula la ruta con el radar de largo alcance. Post: true si la mision puede empezar. */
    private boolean preparar() {
        List<String> motivos = new ArrayList<String>();
        if (!asistente.tieneTablero()) {
            motivos.add("el asistente no tiene un tablero asociado");
        } else {
            if (!asistente.naveViva()) motivos.add("la nave esta destruida");
            if (!asistente.naveDisponible()) motivos.add("la nave no se encuentra disponible");
            // pagar el despegue no puede dejar la nave en 0 (se destruiria) ni con desgaste 100
            if (asistente.getCombustible() - COMBUSTIBLE_DESPEGUE <= 0) motivos.add("no alcanza el combustible");
            if (asistente.getEnergia() - energiaAdicional <= 0) motivos.add("no alcanza la energia");
            if (asistente.getDesgaste() + DESGASTE_DESPEGUE >= 100) motivos.add("el desgaste llegaria al maximo");
        }
        if (!motivos.isEmpty()) {
            return rechazar(motivos);
        }

        posicionInicial = asistente.getPosicion();
        destino = determinarDestino();
        ruta = asistente.calcularRuta(destino);
        if (ruta.isEmpty()) {
            motivos.add("no existe una ruta hacia el destino");
            return rechazar(motivos);
        }
        accion("Ruta calculada con el radar de largo alcance: " + ruta.size() + " casilleros");
        asistente.registrar(nombreMision() + ": preparada");
        return true;
    }

    private boolean rechazar(List<String> motivos) {
        observaciones.addAll(motivos);
        asistente.registrar(nombreMision() + ": rechazada. " + String.join("; ", motivos));
        return false;
    }

    // ------------------------------------------------------------------
    // Fase 2: ejecutar
    // ------------------------------------------------------------------

    private void ejecutar() {
        if (!asistente.aplicarVariacion(-COMBUSTIBLE_DESPEGUE, -energiaAdicional, DESGASTE_DESPEGUE, 0)) {
            fallar("no se pudo descontar el consumo de despegue");
            return;
        }
        accion("Consumo de despegue: " + COMBUSTIBLE_DESPEGUE + " de combustible, " + energiaAdicional
                + " de energia y " + DESGASTE_DESPEGUE + " de desgaste");

        if (!viajar()) {
            return;
        }
        if (!verificarEnDestino()) {
            fallar("al llegar, la nave ya no conserva los recursos que exige la mision");
            return;
        }
        accion("Llegada al destino " + formato(destino));
        exitosa = true;
    }

    /** Ciclo de travesia por turnos. Post: true si la nave llego viva al destino; si no, ya registro el fallo. */
    private boolean viajar() {
        recorrido.add(formato(asistente.getPosicion()));
        int turnos = 0;

        while (asistente.naveViva() && !asistente.getPosicion().equals(destino)) {
            if (turnos++ >= MAX_TURNOS) {
                fallar("se supero el maximo de turnos sin llegar al destino");
                return false;
            }
            // Turno de la nave: inspeccionar, escanear, planificar y mover
            asistente.interactuarConElementoActual();
            if (!asistente.naveViva()) {
                break;
            }
            if (!avanzar()) {
                return false;
            }
            if (!asistente.naveViva()) {
                break;
            }
            // Turno del tablero
            asistente.avanzarTablero();
            if (!pausar()) {
                fallar("la mision fue interrumpida");
                return false;
            }
        }
        if (!asistente.naveViva()) {
            fallar("la nave fue destruida durante el viaje");
            return false;
        }
        return true;
    }

    /**
     * Radar de corto alcance + decision de movimiento: salto warp si hay un obstaculo adelante, salto normal si no.
     * Post: false si la mision no puede continuar (ya registro el fallo)
     */
    private boolean avanzar() {
        Posicion siguiente = siguienteDeLaRuta(asistente.getPosicion());
        if (siguiente == null) {
            fallar("no existe una ruta hacia el destino");
            return false;
        }
        boolean obstaculo = asistente.hayObstaculoEn(siguiente);
        boolean seMovio;
        if (obstaculo) {
            seMovio = asistente.saltarWarp(siguiente);
            if (!seMovio) {
                evento("Obstaculo adelante y el warp no esta disponible: la nave espera");
            }
        } else {
            seMovio = asistente.saltar(siguiente);
            if (!seMovio && asistente.naveViva()) {
                fallar("no hay combustible suficiente para avanzar");
                return false;
            }
        }
        if (seMovio) {
            recorrido.add(formato(asistente.getPosicion()));
            if (obstaculo) {
                evento("Salto warp para esquivar un obstaculo");
            }
        }
        return true;
    }

    /** Si la nave se salio de la ruta (por ejemplo tras un warp), se recalcula desde donde esta. */
    private Posicion siguienteDeLaRuta(Posicion actual) {
        int i = ruta.indexOf(actual);
        if (i < 0) {
            ruta = asistente.calcularRuta(destino);
            accion("Ruta recalculada");
            i = ruta.indexOf(actual);
        }
        if (i < 0 || i + 1 >= ruta.size()) {
            return null;
        }
        return ruta.get(i + 1);
    }

    private boolean pausar() {
        if (pausaMs <= 0) {
            return true;
        }
        try {
            Thread.sleep(pausaMs);
            return true;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
    }

    private void fallar(String motivo) {
        exitosa = false;
        observaciones.add(motivo);
        evento("Mision fallida: " + motivo);
    }

    // ------------------------------------------------------------------
    // Fase 3: evaluar (confecciona el informe final)
    // ------------------------------------------------------------------

    private void evaluar() {
        ajuste = exitosa ? ajusteFinal() : Ajuste.NULO;
        if (exitosa && !ajuste.descripcion.isEmpty()) {
            accion(ajuste.descripcion);
            observaciones.add(ajuste.descripcion);
        }
        // el estado final es el que va a quedar cuando cerrar() impacte el ajuste
        int combustibleFinal = asistente.getCombustible() + ajuste.combustible;
        int energiaFinal = asistente.getEnergia() + ajuste.energia;
        int desgasteFinal = asistente.getDesgaste() + ajuste.desgaste;

        informe = new Informe(
                nombreMision(),
                destino == null ? "sin determinar" : descripcionObjetivo(destino),
                exitosa,
                new ArrayList<String>(acciones),
                new ArrayList<String>(recorrido),
                new ArrayList<String>(eventosRelevantes),
                Math.max(0, combustibleInicial - combustibleFinal),
                Math.max(0, energiaInicial - energiaFinal),
                Math.max(0, desgasteFinal - desgasteInicial),
                combustibleFinal, energiaFinal, desgasteFinal,
                new ArrayList<String>(observaciones));
    }

    // ------------------------------------------------------------------
    // Fase 4: cerrar (impacta de forma definitiva en la nave)
    // ------------------------------------------------------------------

    private void cerrar() {
        if (exitosa && !asistente.aplicarVariacion(ajuste.combustible, ajuste.energia, ajuste.desgaste, ajuste.gemas)) {
            asistente.registrar(nombreMision() + ": no se pudo aplicar el cierre");
        }
        asistente.registrar(nombreMision() + ": cerrada (" + (exitosa ? "exito" : "sin exito") + ")");
    }

    // ------------------------------------------------------------------
    // Lo que cambia en cada mision
    // ------------------------------------------------------------------

    public abstract String nombreMision();

    /** Post: coordenada a la que debe llegar la nave. Se llama una sola vez, en preparar. */
    protected abstract Posicion determinarDestino();

    /** Post: texto del objetivo para el informe. Pre: destino != null */
    protected abstract String descripcionObjetivo(Posicion destino);

    /**
     * Verificacion al llegar al destino. Por defecto no exige nada.
     * Post: false si la mision debe darse por fallida
     */
    protected boolean verificarEnDestino() {
        return true;
    }

    /** Pre: la nave llego al destino. Post: entrega, recompensa o recarga final (se aplica en cerrar). */
    protected abstract Ajuste ajusteFinal();

    // ------------------------------------------------------------------
    // Utilidades para las subclases
    // ------------------------------------------------------------------

    protected final Asistente asistente() {
        return asistente;
    }

    protected final Posicion posicionInicial() {
        return posicionInicial;
    }

    protected static String formato(Posicion p) {
        return "(" + p.getX() + ", " + p.getY() + ")";
    }

    private void accion(String texto) {
        acciones.add(texto);
        asistente.registrar(nombreMision() + ": " + texto);
    }

    private void evento(String texto) {
        eventosRelevantes.add(texto);
        asistente.registrar(nombreMision() + ": " + texto);
    }

    // ------------------------------------------------------------------
    // Consultas
    // ------------------------------------------------------------------

    /** Post: energia que consume el despegue de esta mision (M-01: 5, M-02: 10, M-03: 0). */
    public final int energiaAdicional() {
        return energiaAdicional;
    }

    public final boolean isExitoMision() {
        return exitosa;
    }

    /** Pre: ms >= 0. Pausa entre turnos para poder ver el avance en la vista. */
    public final void setPausaMs(int ms) {
        if (ms < 0) {
            throw new IllegalArgumentException("La pausa no puede ser negativa");
        }
        this.pausaMs = ms;
    }
}