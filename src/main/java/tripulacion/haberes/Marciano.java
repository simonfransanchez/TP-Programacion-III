package tripulacion.haberes;

public class Marciano extends OrigenDecorator {

    public Marciano(Haber decorado) {
        super(decorado);
    }

    @Override
    protected double getSubsidio() {
        return 18;
    }
}