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
    protected void evaluarResultado() {}

    @Override
    protected void ejecutar() {
    }

    @Override
    protected void cerrar() {}

    @Override
    public int energiaAdicional() {
        return 0;
    }
}
