package misiones;

import java.util.List;

public class Informe {
    private final String mision;
    private final boolean exitosa;
    private final List<String> acciones;
    private final int combustibleConsumido;
    private final int energiaConsumida;
    private final int desgasteGenerado;
    private final int combustibleFinal;
    private final int energiaFinal;
    private final int desgasteFinal;
    private final List<String> observaciones;

    public Informe(String mision, boolean exitosa, List<String> acciones, int combustibleConsumido, int energiaConsumida, int desgasteGenerado, int combustibleFinal, int energiaFinal, int desgasteFinal, List<String> observaciones) {
        this.mision = mision;
        this.exitosa = exitosa;
        this.acciones = acciones;
        this.combustibleConsumido = combustibleConsumido;
        this.energiaConsumida = energiaConsumida;
        this.desgasteGenerado = desgasteGenerado;
        this.combustibleFinal = combustibleFinal;
        this.energiaFinal = energiaFinal;
        this.desgasteFinal = desgasteFinal;
        this.observaciones = observaciones;
    }
}
