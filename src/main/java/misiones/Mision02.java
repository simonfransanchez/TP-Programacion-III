package misiones;

import asistente.Asistente;

public class Mision02 extends Mision{

    protected Mision02() {
        System.out.println(nombreMision());
    }

    @Override
    protected String nombreMision() {
        return "M-02";
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
