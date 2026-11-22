package misiones;

import asistente.Asistente;
import destinoytablero.Posicion;

import java.util.concurrent.ThreadLocalRandom;

/**
 * M-02 Recoleccion: llegar al punto de interes y extraer gemas.
 * Despegue: 4 de combustible, 10 de energia, 4 de desgaste. Recompensa: entre 20 y 100 gemas.
 */
public class Mision02 extends Mision {

    private static final int MINIMO_GEMAS = 20;
    private static final int MAXIMO_GEMAS = 100;

    private final int gemas;

    /** Recolecta una cantidad aleatoria de gemas entre 20 y 100. */
    public Mision02(Asistente asistente) {
        this(asistente, ThreadLocalRandom.current().nextInt(MINIMO_GEMAS, MAXIMO_GEMAS + 1));
    }

    /**
     * Pre:  20 <= gemas <= 100
     * Post: la mision recolecta esa cantidad al llegar (permite pruebas reproducibles)
     */
    public Mision02(Asistente asistente, int gemas) {
        super(asistente, 10);
        if (gemas < MINIMO_GEMAS || gemas > MAXIMO_GEMAS) {
            throw new IllegalArgumentException("Las gemas van de " + MINIMO_GEMAS + " a " + MAXIMO_GEMAS);
        }
        this.gemas = gemas;
    }

    @Override
    public String nombreMision() {
        return "M-02";
    }

    @Override
    protected Posicion determinarDestino() {
        return asistente().obtenerObjetivo();           // punto de interes
    }

    @Override
    protected String descripcionObjetivo(Posicion destino) {
        return "Punto de recoleccion en " + formato(destino);
    }

    @Override
    protected Ajuste ajusteFinal() {
        return new Ajuste("Recoleccion: " + gemas + " gemas obtenidas", 0, 0, 0, gemas);
    }
}