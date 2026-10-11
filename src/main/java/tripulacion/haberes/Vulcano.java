package tripulacion.haberes;

public class Vulcano extends OrigenDecorator {

    public Vulcano(Haber decorado) {
        super(decorado);
    }

    @Override
    protected double getSubsidiomensual() {
        return 30;
    }
}