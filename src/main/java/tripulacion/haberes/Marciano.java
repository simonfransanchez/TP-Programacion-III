package tripulacion.haberes;

public class Marciano extends OrigenDecorator {

    public Marciano(Haber decorado) {
        super(decorado);
    }

    @Override
    protected double getSubsidiomensual() {
        return 18;
    }
}