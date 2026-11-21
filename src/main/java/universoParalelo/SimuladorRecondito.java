
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

    public void informarEvento(String mensaje) {
        synchronized (this) {
            eventos.add(mensaje);
        }

        notificarObservadores();
    }

    public synchronized List<String> getEventosDesde(int indice) {
        return new ArrayList<>(
            eventos.subList(indice, eventos.size())
        );
    }

    // CREAR GENERADORAS

    public synchronized void crearGeneradora() {
        if (detenido) return;

        cantidadGeneradoras++;

        GeneradoraDeEnergia generadora =new GeneradoraDeEnergia(plataforma, this, cantidadGeneradoras);

        generadora.setName("Generadora " + cantidadGeneradoras);
        hilos.add(generadora);
        informarEvento("Ingresó una nueva Generadora al universo paralelo");

        generadora.start();
    }

    // CREAR NAVES CONSUMIDORAS

    public synchronized void crearNaveConsumidora() {
        if (detenido) return;

        cantidadNaves++;

        NaveConsumidora nave =new NaveConsumidora(plataforma, this, cantidadNaves);

        nave.setName("Nave " + cantidadNaves);
        hilos.add(nave);
        informarEvento("Ingresó una nueva Nave al universo paralelo");
        nave.start();
    }


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
