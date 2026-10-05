package misiones;

import java.util.Objects;


public abstract class Mision {
    protected final Asistente asistente;
    private int combustible_requerido;
    private int energia_requerida;

    protected Mision(Asistente asistente) {
        this.asistente = Objects.requireNonNull(asistente, "Falta asignar un asistente para la mision");
    }

    public Informe realizarMision () {
        Informe informe = null;
        if (preparaciones()) {
            ejecutar();
            evaluarResultado();
            informe = cerrar();
        }
        else
            System.out.println("La nave no cumple con los requisitos para llevar a cabo la mision");
        return informe;
    }

    private boolean preparaciones() {
        boolean cumple = false;

        if (asistente.naveDisponible())
            if (asistente.getCombustible() >= combustible_requerido)
                if (asistente.getEnergia() >= energia_requerida)
                    if (asistente.objetivoAccesible()) {
                        cumple = true;
                    } else
                        System.out.println("El objetivo no es accesible");
                else
                    System.out.println("No alcanza la energia para realizar la mision");
            else
               System.out.println("No alcanza el combustible para realizar la mision");
        else
           System.out.println("La nave no se encuentra disponible para realizar la mision");

        return cumple;
    }

    protected abstract void evaluarResultado();

    protected abstract void ejecutar();

    protected abstract Informe cerrar();
}
}