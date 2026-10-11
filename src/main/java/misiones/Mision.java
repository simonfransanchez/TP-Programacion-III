package misiones;

import asistente.Asistente;

import java.util.Objects;


public abstract class Mision {
    private final Asistente asistente;
    private int combustibleRequerido;
    private int desgaste;
    private String motivoRechazo;
    private boolean exitoMision;

    public Mision(Asistente asistente) {
        this.asistente = Objects.requireNonNull(asistente, "Falta asignar un asistente para la mision");
        this.combustibleRequerido = 4;
        this.desgaste = 4;
    }

    public final Informe realizarMision () {
        Informe informe = null;
        if (preparaciones()) {
            ejecutar();
            //asistente.agregaDesgaste(desgaste);
            exitoMision = evaluarResultado();
            if (exitoMision) {
                asistente.registrar(nombreMision() + "Mision cumplida");
                asistente.cargarEnergia(energiaExtra());
            }
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
        if (asistente.requiereMantenimiento()){
            this.motivoRechazo = "la nave requiere mantenimiento";
        return false;
        }
        return true;
    }

    protected abstract String nombreMision();

    protected abstract boolean evaluarResultado();

    protected abstract void ejecutar();

    protected abstract Informe cerrar();

    protected int energiaExtra(){
        return 5;
    }
}