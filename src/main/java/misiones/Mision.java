package misiones;


import naves.NaveBase;

public abstract class Mision {
    private int combustibleRequerido;
    private int desgaste;
    private String motivoRechazo;
    private boolean exitoMision;

    public Mision() {
        this.combustibleRequerido = 4;
        this.desgaste = 4;
    }

    public final Informe realizarMision () {
        Informe informe = null;
            preparar();
            ejecutar();
            //asistente.agregaDesgaste(desgaste);
            exitoMision = evaluarResultado();
            informe = cerrar();
        }

    private boolean puedeCumplir(NaveBase nave) {
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

    protected abstract void preparar();

    protected abstract String nombreMision();

    protected abstract boolean evaluarResultado();

    protected abstract void ejecutar();

    protected abstract Informe cerrar();

    
}