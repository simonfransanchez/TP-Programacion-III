package misiones;

import java.util.Objects;


public abstract class Mision {
    private final Asistente asistente;
    private int combustibleRequerido;
    private int energiaRequerida;
    private String motivoRechazo;
    private boolean exitoMision;

    public Mision(Asistente asistente) {
        this.asistente = Objects.requireNonNull(asistente, "Falta asignar un asistente para la mision");
        this.combustibleRequerido = 4;
    }

    public final Informe realizarMision () {
        Informe informe = null;
        if (preparaciones()) {
            ejecutar();
            evaluarResultado();
            informe = cerrar();
        }
        else
            asistente.registrar(nombreMision() + ": rechazada. " + motivoRechazo);
        return informe;
    }


    private boolean preparaciones() {
        if (!asistente.naveDisponible()) {
            this.motivoRechazo = "la nave no se encuentra disponible";
            return false;
        }
        if (asistente.getCombustible() < combustibleRequerido) {
            this.motivoRechazo = "no alcanza el combustible";
            return false;
        }
        if (asistente.getEnergia() < energiaRequerida) {
            this.motivoRechazo = "no alcanza la energía";
            return false;
        }
        return true;
    }

    protected abstract String nombreMision();

    protected abstract void evaluarResultado();

    protected abstract void ejecutar();

    protected abstract Informe cerrar();
}