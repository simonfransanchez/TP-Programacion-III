package misiones;

import java.util.List;

public class Informe {
    private final String mision;
    private final String objetivo;
    private final boolean exitosa;
    private final List<String> acciones;
    private final List<String> recorrido;
    private final List<String> eventosRelevantes;
    private final int combustibleConsumido;
    private final int energiaConsumida;
    private final int desgasteGenerado;
    private final int combustibleFinal;
    private final int energiaFinal;
    private final int desgasteFinal;
    private final List<String> observaciones;

    public Informe(String mision, String objetivo, boolean exitosa, List<String> acciones, List<String> recorrido, List<String> eventosRelevantes, int combustibleConsumido, int energiaConsumida, int desgasteGenerado, int combustibleFinal, int energiaFinal, int desgasteFinal, List<String> observaciones) {
        this.mision = mision;
        this.objetivo = objetivo;
        this.exitosa = exitosa;
        this.acciones = acciones;
        this.recorrido = recorrido;
        this.eventosRelevantes = eventosRelevantes;
        this.combustibleConsumido = combustibleConsumido;
        this.energiaConsumida = energiaConsumida;
        this.desgasteGenerado = desgasteGenerado;
        this.combustibleFinal = combustibleFinal;
        this.energiaFinal = energiaFinal;
        this.desgasteFinal = desgasteFinal;
        this.observaciones = observaciones;
    }


    @Override
    public String toString() {
        return "Informe{" +
                "mision='" + mision + '\'' +
                ", exitosa=" + exitosa +
                ", acciones=" + acciones +
                ", combustibleConsumido=" + combustibleConsumido +
                ", energiaConsumida=" + energiaConsumida +
                ", desgasteGenerado=" + desgasteGenerado +
                ", combustibleFinal=" + combustibleFinal +
                ", energiaFinal=" + energiaFinal +
                ", desgasteFinal=" + desgasteFinal +
                ", observaciones=" + observaciones +
                '}';
    }

    public String getMision() {
        return mision;
    }

    public boolean isExitosa() {
        return exitosa;
    }

    public List<String> getAcciones() {
        return acciones;
    }

    public int getCombustibleConsumido() {
        return combustibleConsumido;
    }

    public int getEnergiaConsumida() {
        return energiaConsumida;
    }

    public int getDesgasteGenerado() {
        return desgasteGenerado;
    }

    public int getCombustibleFinal() {
        return combustibleFinal;
    }

    public int getEnergiaFinal() {
        return energiaFinal;
    }

    public int getDesgasteFinal() {
        return desgasteFinal;
    }

    public List<String> getObservaciones() {
        return observaciones;
    }
}
