package misiones;

public class Mision01 extends Mision {

    public Mision01(Asistente asistente) {
        super(asistente);
        System.out.println(nombreMision());
    }

    @Override
    protected String nombreMision() {
        return "M1";
    }

    @Override
    protected void evaluarResultado() {
    }

    @Override
    protected void ejecutar() {
    }

    @Override
    protected Informe cerrar() {
        return null;
    }
}
