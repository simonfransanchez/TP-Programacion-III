package universoParalelo;

public class NaveConsumidora extends Thread {

    private final Plataforma plataforma;
    private final SimuladorRecondito simulador;
    private final int id;

    public NaveConsumidora(Plataforma plataforma, SimuladorRecondito simulador, int id) {
        this.plataforma = plataforma;
        this.simulador = simulador;
        this.id = id;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                simulador.informarEvento("Nave " + id + " solicitando bateria");
                if (!plataforma.retirar()) {
                    break;
                }
                simulador.informarEvento("Nave " + id + " retiro y utilizo una bateria");
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                interrupt();
                break;
            }
        }
    }
}
