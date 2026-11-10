
public class Plataforma {
    private int baterias;
    private final int capacidad;
    private SimuladorRecondito simulador;

    public Plataforma(int capacidad) {
        this.capacidad = capacidad;
        this.baterias = 0;
    }

    public void setSimulador(SimuladorRecondito simulador) {
        this.simulador = simulador;
    }

    public synchronized boolean depositar() {
        while (baterias == capacidad) {
            simulador.informarEvento(Thread.currentThread().getName() + " esperando a que se libere espacio en la plataforma");
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return false;
            }
        }
        baterias++;
        notifyAll();
        return true;
    }

    public synchronized boolean retirar() {
        while (baterias == 0) {
            simulador.informarEvento(Thread.currentThread().getName() + " esperando por falta de baterias en la plataforma");
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return false;
            }
        }
        baterias--;
        notifyAll();
        return true;
    }

    public synchronized int getBaterias() {
        return baterias;
    }
}
