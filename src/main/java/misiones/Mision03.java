package misiones;

import asistente.Asistente;
import destinoytablero.Posicion;

/**
 * M-03 Retorno a punto seguro: volver a la base de origen, recargar al 100% y reparar el desgaste.
 * Despegue: 4 de combustible, 0 de energia, 4 de desgaste.
 */
public class Mision03 extends Mision {

    private static final int CAPACIDAD = 100;

    public Mision03(Asistente asistente) {
        super(asistente, 0);
    }

    @Override
    public String nombreMision() {
        return "M-03";
    }

    @Override
    protected Posicion determinarDestino() {
        return asistente().getBase();                   // punto de partida del recorrido
    }

    @Override
    protected String descripcionObjetivo(Posicion destino) {
        return "Base de origen en " + formato(destino);
    }

    @Override
    protected Ajuste ajusteFinal() {
        return new Ajuste("Recarga total y mantenimiento general en la base",
                CAPACIDAD - asistente().getCombustible(),
                CAPACIDAD - asistente().getEnergia(),
                -asistente().getDesgaste(), 0);
    }
}