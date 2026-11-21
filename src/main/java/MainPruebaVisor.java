import controlador.ControladorVisor;
import universoParalelo.SimuladorRecondito;
import vista.paneles.PanelVisor;

import javax.swing.*;

public class MainPruebaVisor {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            SimuladorRecondito simulador =
                    new SimuladorRecondito(5);

            PanelVisor vista = new PanelVisor(simulador);

            new ControladorVisor(vista, simulador);

            JFrame ventana = new JFrame("Visor Recóndito");
            ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            ventana.setSize(650, 450);
            ventana.add(vista);
            ventana.setLocationRelativeTo(null);
            ventana.setVisible(true);
        });
    }
}