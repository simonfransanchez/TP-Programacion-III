package misiones;


import asistente.Asistente;

import java.util.List;

/**
 * Inv: asistente != null && informe != null
 * Inv: combustibleRequerido >= 0 && energiaRequerida >= 0
 */
public abstract class Mision {
    private final int desgaste;
    private final int combustibleRequerido;
    private final int energiaRequerida;
    private boolean exitoMision;
    private Informe informe;
    private Asistente asistente;

    public Mision(Asistente asistente) {
        this.asistente = asistente;
        this.combustibleRequerido = 4;
        this.desgaste = 4;
        this.energiaRequerida = energiaAdicional();
        informe = new Informe(
                "",               // mision
                "", // objetivo
                false,                              // exitosa
                List.of(), // acciones
                List.of(),       // recorrido
                List.of(), // eventosRelevantes
                0,  // combustibleConsumido
                0,  // energiaConsumida
                0,  // desgasteGenerado
                0, // combustibleFinal
                0,  // energiaFinal
                0,  // desgasteFinal
                List.of() // observaciones
        );
    }

    /**
     * Pre:  la mision fue creada con un asistente != null
     * Post: devuelve el informe (nunca null) y lo agrega a la bitacora
     * Post: si puedeCumplir() era true, se ejecutaron en este orden:
     *       preparar -> ejecutar -> evaluarResultado -> cerrar
     * Post: si puedeCumplir() era false, no se ejecuto ninguno de esos pasos
     *       y se registro el motivo del rechazo
     */
    public final Informe realizarMision() {     // la idea es que dentro de los metodo actualiza el informe
        System.out.println(nombreMision());     // y los recursos de la nave mediante el asistente
        if (puedeCumplir()) {
            System.out.println("Preparando mision");
            preparar();
            System.out.println("Ejecutando mision");
            ejecutar();
            System.out.println("Evaluando mision");
            evaluarResultado();
            System.out.println("Terminando mision");
            cerrar();
        }
        asistente.agregarInformeBitacora(this.informe);
        return this.informe;
    }

    /**
     * Post: devuelve true si y solo si
     *       asistente.naveDisponible()
     *       && asistente.getCombustible() >= combustibleRequerido
     *       && asistente.getEnergia() >= energiaRequerida
     */
    private boolean puedeCumplir() {
        String motivoRechazo = "";

        if (!asistente.naveDisponible())
            motivoRechazo = "la nave no se encuentra disponible";

        if (asistente.getCombustible() < combustibleRequerido)
            motivoRechazo = "no alcanza el combustible";

        if (asistente.getEnergia() < energiaRequerida)
            motivoRechazo = "no alcanza la energia";

        if (!motivoRechazo.equals("")){
            asistente.registrar(nombreMision() + motivoRechazo);
            return false;
        }

        return true;
    }
    public abstract String nombreMision();

    /** Pre (todos los pasos): puedeCumplir() == true
     * Los metodos se encargan de la carga del informe y la actualizacion de los recursos
     */
    protected abstract void preparar();
    protected abstract void evaluarResultado();
    protected abstract void ejecutar();
    protected abstract void cerrar();

    /**
     * Post: devuelve un valor >= 0 (M-01: 5, M-02: 5, M-03: 0)
     */
    public abstract int energiaAdicional();

    public int getCombustibleRequerido() { return combustibleRequerido; }

    public int getEnergiaRequerida() {
        return energiaRequerida;
    }

    public boolean isExitoMision() {
        return exitoMision;
    }

    public void setExitoMision(boolean exitoMision) {
        this.exitoMision = exitoMision;
    }
}