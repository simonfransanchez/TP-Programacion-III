package tripulacion.haberes;

public abstract class HaberDecorator implements Haber {
    protected final Haber decorado;

    /**
     * Pre:  decorado != null (si no, lanza IllegalArgumentException)
     * Inv:  decorado != null
     */
    public HaberDecorator(Haber decorado) {
        if (decorado == null) {
            throw new IllegalArgumentException("El haber a decorar no puede ser nulo");
        }
        this.decorado = decorado;
    }
}
