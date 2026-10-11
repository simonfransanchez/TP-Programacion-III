package misiones;

import asistente.Asistente;

public class Mision01 extends Mision {

    public Mision01(Asistente asistente) {super(asistente);}

    @Override
    protected void preparar() {}

    @Override
    public String nombreMision() {
        return "M-01";
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
