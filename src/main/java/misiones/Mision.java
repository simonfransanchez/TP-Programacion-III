package misiones;


import asistente.Asistente;
import naves.NaveBase;

public abstract class Mision {
    private final int desgaste;
    private final int combustibleRequerido;
    private final int energiaRequerida;
    private boolean exitoMision;
    private Informe informe = null;
    private Asistente asistente;

    public Mision(Asistente asistente) {
        this.asistente = asistente;
        this.combustibleRequerido = 4;
        this.desgaste = 4;
        this.energiaRequerida = energiaAdicional();
    }

    public final Informe realizarMision() {     // la idea es que dentro de los metodo actualiza el informe
        System.out.println(nombreMision());     // y los recursos de la nave mediante el asistente
        if (puedeCumplir()) {
            preparar();
            ejecutar();
            evaluarResultado();
            cerrar();
        }
        return this.informe;
    }

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

    protected abstract void preparar();

    public abstract String nombreMision();

    protected abstract boolean evaluarResultado();

    protected abstract void ejecutar();

    protected abstract Informe cerrar();

    public abstract int energiaAdicional();

    public int getCombustibleRequerido() {
        return combustibleRequerido;
    }

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