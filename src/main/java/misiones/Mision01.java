package misiones;

import asistente.Asistente;

public class Mision01 extends Mision {

    public Mision01(Asistente asistente) {
        super(asistente);
        System.out.println(nombreMision());
    }

    @Override
    protected String nombreMision() {
        return "M-01";
    }

    @Override
    protected boolean evaluarResultado() {
        return true;
    }

    @Override
    protected void ejecutar() {
    }

    @Override
    protected Informe cerrar() {
        return null;
    }
}
