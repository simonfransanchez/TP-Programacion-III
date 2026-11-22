
package universoParalelo;

import destinoytablero.Observable;
import java.util.ArrayList;
import java.util.List;

public class SimuladorRecondito extends Observable {

    private final Plataforma plataforma;

    private int cantidadGeneradoras = 0;
    private int cantidadNaves = 0;

    private final List<String> eventos = new ArrayList<>();
    private final List<Thread> hilos = new ArrayList<>();

    private boolean detenido = false;

    public SimuladorRecondito(int capacidad) {
        this.plataforma = new Plataforma(capacidad);
        this.plataforma.setSimulador(this);
    }

    // REGISTRAR EVENTOS Y NOTIFICAR OBSERVADORES

    public void informarEvento(String mensaje) {
        synchronized (this) {
            eventos.add(mensaje);
        }

        notificarObservadores();
    }

    // OBTENER EVENTOS NUEVOS PARA PANELVISOR

    public synchronized List<String> getEventosDesde(int indice) {
        return new ArrayList<>(eventos.subList(indice, eventos.size()));
    }

    // COMPATIBILIDAD CON PANELVISORRECONDITO

    public synchronized ArrayList<String> getEventos() {
        return new ArrayList<>(eventos);
    }

    // CREAR GENERADORA

    public synchronized void crearGeneradora() {
        if (detenido) return;

        cantidadGeneradoras++;

        GeneradoraDeEnergia generadora =
            new GeneradoraDeEnergia(
                plataforma, this, cantidadGeneradoras
            );

        generadora.setName("Generadora " + cantidadGeneradoras);
        hilos.add(generadora);

        informarEvento(
            "Ingresó una nueva Generadora al universo paralelo"
        );

        generadora.start();
    }

    // CREAR NAVE CONSUMIDORA

    public synchronized void crearNaveConsumidora() {
        if (detenido) return;

        cantidadNaves++;

        NaveConsumidora nave =
            new NaveConsumidora(
                plataforma, this, cantidadNaves
            );

        nave.setName("Nave " + cantidadNaves);
        hilos.add(nave);

        informarEvento(
            "Ingresó una nueva Nave al universo paralelo"
        );

        nave.start();
    }

    // DETENER SIMULACIÓN

    public void detenerSimulacion() {
        List<Thread> copia;

        synchronized (this) {
            if (detenido) return;

            detenido = true;
            copia = new ArrayList<>(hilos);
        }

        for (Thread hilo : copia) {
            hilo.interrupt();
        }

        informarEvento("Simulación detenida");
    }
}
