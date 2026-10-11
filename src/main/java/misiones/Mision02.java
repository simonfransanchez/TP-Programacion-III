package misiones;

import asistente.Asistente;

public class Mision02 extends Mision{

    protected Mision02(Asistente asistente) {
        super(asistente);
    }

    @Override
    protected void preparar() {}

    @Override
    public String nombreMision() {
        return "M-02";
    }

    @Override
    protected void evaluarResultado() {}

    @Override
    protected void ejecutar() {}

    @Override
    protected void cerrar() {}

    @Override
    public int energiaAdicional() {
        return 5;
    }
}
