
package universoParalelo;

import controlador.ControladorVisorRecondito;
import vista.paneles.PanelVisorRecondito;
import javax.swing.*;

public class MainPruebaVisor {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            SimuladorRecondito simulador = new SimuladorRecondito(5);
            PanelVisorRecondito panel = new PanelVisorRecondito(simulador);

            new ControladorVisorRecondito(simulador, panel);

            JFrame ventana = new JFrame("Visor Recondito");
            ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            ventana.add(panel);
            ventana.setSize(750, 450);
            ventana.setLocationRelativeTo(null);
            ventana.setVisible(true);
        });
    }
}
