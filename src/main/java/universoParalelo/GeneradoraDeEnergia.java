package universoParalelo;

public class GeneradoraDeEnergia extends Thread {

    private final Plataforma plataforma;
    private final SimuladorRecondito simulador;
    private final int id;

    public GeneradoraDeEnergia(Plataforma plataforma, SimuladorRecondito simulador, int id) {
        this.plataforma = plataforma;
        this.simulador = simulador;
        this.id = id;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                Thread.sleep(1000);
                simulador.informarEvento("Generadora " + id + " produjo una bateria");
                if (!plataforma.depositar()) {
                    break;
                }
                simulador.informarEvento("Generadora " + id + " deposito una bateria");
            } catch (InterruptedException e) {
                interrupt();
                break;
            }
        }
    }
}
