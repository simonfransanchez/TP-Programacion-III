package misiones;

import asistente.Asistente;

public class Mision03 extends Mision{

    protected Mision03(Asistente asistente) {
        super(asistente);
    }

    @Override
    protected void preparar() {}

    @Override
    public String nombreMision() {
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
    public int energiaAdicional() {
        return 0;
    }
}
