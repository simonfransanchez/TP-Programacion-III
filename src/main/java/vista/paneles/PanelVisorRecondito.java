
package vista.paneles;

import destinoytablero.Observador;
import universoParalelo.SimuladorRecondito;

import javax.swing.*;
import java.awt.*;

public class PanelVisorRecondito extends JPanel implements Observador {

    private final SimuladorRecondito simulador;
    private final JTextArea areaEventos;

    public PanelVisorRecondito(SimuladorRecondito simulador) {
        this.simulador = simulador;
        setLayout(new BorderLayout());
        areaEventos = new JTextArea();
        areaEventos.setEditable(false);
        add(new JScrollPane(areaEventos), BorderLayout.CENTER);
        simulador.agregarObservador(this);
    }

    @Override
    public void actualizar() {
        StringBuilder texto = new StringBuilder();
        for (String evento : simulador.getEventos()) {
            texto.append(evento).append("\n");
        }
        areaEventos.setText(texto.toString());
        areaEventos.setCaretPosition(areaEventos.getDocument().getLength());
    }
}
