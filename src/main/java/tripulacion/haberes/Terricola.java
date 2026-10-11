package tripulacion.haberes;

public class Terricola extends OrigenDecorator {

    public Terricola(Haber decorado) {
        super(decorado);
    }

    @Override
    protected double getSubsidiomensual() {
        return 20;
    }
}