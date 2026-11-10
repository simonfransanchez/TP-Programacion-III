package universoParalelo;

import destinoytablero.Observable;
import java.util.ArrayList;
import javax.swing.SwingUtilities;

public class SimuladorRecondito extends Observable {

    private final Plataforma plataforma;
    private int cantidadGeneradoras = 0;
    private int cantidadNaves = 0;
    private final ArrayList<String> eventos = new ArrayList<>();

    public SimuladorRecondito(int capacidad) {
        this.plataforma = new Plataforma(capacidad);
        this.plataforma.setSimulador(this);
    }


    public void informarEvento(String mensaje) {
        synchronized (eventos) {
            eventos.add(mensaje);
        }
        SwingUtilities.invokeLater(() -> notificarObservadores());
    }

    public ArrayList<String> getEventos() {
        synchronized (eventos) {
            return new ArrayList<>(eventos);
        }
    }


    public void crearGeneradora() {
        cantidadGeneradoras++;
        GeneradoraDeEnergia generadora =new GeneradoraDeEnergia(plataforma, this, cantidadGeneradoras);
        informarEvento("Ingreso una nueva Generadora al universo paralelo");
        generadora.setName("Generadora " + cantidadGeneradoras);
        generadora.start();
    }

    public void crearNaveConsumidora() {
        cantidadNaves++;
        NaveConsumidora nave =new NaveConsumidora(plataforma, this, cantidadNaves);
        informarEvento("Ingreso una nueva Nave al universo paralelo");
        nave.setName("Nave " + cantidadNaves);
        nave.start();
    }
}