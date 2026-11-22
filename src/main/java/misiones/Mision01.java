package misiones;

import asistente.Asistente;
import destinoytablero.Posicion;

import java.util.concurrent.ThreadLocalRandom;

/**
 * M-01 Interceptacion y asistencia: llegar a la nave aliada y transferirle recursos de auxilio.
 * Despegue: 4 de combustible, 5 de energia, 4 de desgaste. Entrega: entre 0 y 50 de cada recurso.
 */
public class Mision01 extends Mision {

    private static final int MAXIMO_ENTREGA = 50;

    private final int combustibleAEntregar;
    private final int energiaAEntregar;

    /** Entrega un monto aleatorio entre 0 y 50 de cada recurso. */
    public Mision01(Asistente asistente) {
        this(asistente, aleatorio(), aleatorio());
    }

    /**
     * Pre:  0 <= combustible <= 50 && 0 <= energia <= 50
     * Post: la mision entrega esos montos al llegar (permite pruebas reproducibles)
     */
    public Mision01(Asistente asistente, int combustible, int energia) {
        super(asistente, 5);
        if (combustible < 0 || combustible > MAXIMO_ENTREGA || energia < 0 || energia > MAXIMO_ENTREGA) {
            throw new IllegalArgumentException("Cada entrega debe estar entre 0 y " + MAXIMO_ENTREGA);
        }
        this.combustibleAEntregar = combustible;
        this.energiaAEntregar = energia;
    }

    private static int aleatorio() {
        return ThreadLocalRandom.current().nextInt(0, MAXIMO_ENTREGA + 1);
    }

    @Override
    public String nombreMision() {
        return "M-01";
    }

    @Override
    protected Posicion determinarDestino() {
        return asistente().obtenerObjetivo();           // posicion de la nave aliada
    }

    @Override
    protected String descripcionObjetivo(Posicion destino) {
        return "Nave aliada en " + formato(destino);
    }

    /** La nave tiene que conservar lo que va a entregar y quedar con recursos (no en 0). */
    @Override
    protected boolean verificarEnDestino() {
        return asistente().getCombustible() > combustibleAEntregar
                && asistente().getEnergia() > energiaAEntregar;
    }

    @Override
    protected Ajuste ajusteFinal() {
        return new Ajuste("Entrega a la nave aliada: " + combustibleAEntregar + " de combustible y "
                + energiaAEntregar + " de energia", -combustibleAEntregar, -energiaAEntregar, 0, 0);
    }
}