package misiones;

public class Mision02 extends Mision{

    protected Mision02(Asistente asistente) {
        super(asistente);
        System.out.println(nombreMision());
    }

    @Override
    protected String nombreMision() {
        return "M2";
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
