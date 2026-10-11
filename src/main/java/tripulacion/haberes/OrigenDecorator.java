package tripulacion.haberes;

public abstract class OrigenDecorator extends HaberDecorator {

    public OrigenDecorator(Haber decorado) {
        super(decorado);
    }

    protected abstract double getSubsidiomensual();

    /**
     * Post: devuelve decorado.calcularHaber() + getSubsidiomensual()
     */
    @Override
    public double calcularHaber() {
        return decorado.calcularHaber() + getSubsidiomensual();
    }
}