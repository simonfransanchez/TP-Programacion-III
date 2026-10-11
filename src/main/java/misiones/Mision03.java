package misiones;

import asistente.Asistente;

public class Mision03 extends Mision{
    protected Mision03() {
        System.out.println(nombreMision());
    }

    @Override
    protected String nombreMision() {
        return "M-03";
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

    @Override
    protected int energiaExtra() {
        return 0;
    }
}
