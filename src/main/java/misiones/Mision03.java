package misiones;

public class Mision03 extends Mision{
    protected Mision03(Asistente asistente) {
        super(asistente);
        System.out.println(nombreMision());
    }

    @Override
    protected String nombreMision() {
        return "M3";
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
