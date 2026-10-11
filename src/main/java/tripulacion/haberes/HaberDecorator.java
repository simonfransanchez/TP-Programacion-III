package tripulacion.haberes;

public abstract class HaberDecorator implements Haber {
    protected final Haber decorado;

    public HaberDecorator(Haber decorado) {
        if (decorado == null) {
            throw new IllegalArgumentException("El haber a decorar no puede ser nulo");
        }
        this.decorado = decorado;
    }
}
