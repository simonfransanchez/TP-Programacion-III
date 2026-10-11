package tripulacion.haberes;

public class AdicionalConsejos extends HaberDecorator {
    private static final double PGxconsejo = 2;

    private final int consejos;

    /**
     * Pre:  decorado != null && consejos >= 0
     *       (si no, lanza IllegalArgumentException)
     */
    public AdicionalConsejos(Haber decorado, int consejos) {
        super(decorado);
        if (consejos < 0) {
            throw new IllegalArgumentException("La cantidad de consejos no puede ser negativa: " + consejos);
        }
        this.consejos = consejos;
    }

    /**
     * Post: devuelve decorado.calcularHaber() + 2 * consejos
     */
    @Override
    public double calcularHaber() {
        return decorado.calcularHaber() + PGxconsejo * consejos;
    }
}
