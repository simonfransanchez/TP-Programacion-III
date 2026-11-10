
package vista.paneles;
import destinoytablero.Observador;
import universoParalelo.SimuladorRecondito;

import javax.swing.*;
import java.awt.*;

public class PanelVisorRecondito extends JPanel implements Observador {

    private final SimuladorRecondito simulador;
    private final JTextArea areaEventos;
    private final JButton botonGeneradora;
    private final JButton botonNave;

    public PanelVisorRecondito(SimuladorRecondito simulador) {
        this.simulador = simulador;
        setLayout(new BorderLayout());
        areaEventos = new JTextArea();
        areaEventos.setEditable(false);
        add(new JScrollPane(areaEventos), BorderLayout.CENTER);
        JPanel panelBotones = new JPanel();
        botonGeneradora = new JButton("Crear Generadora de Energia");
        botonNave = new JButton("Crear Nave Consumidora");
        panelBotones.add(botonGeneradora);
        panelBotones.add(botonNave);
        add(panelBotones, BorderLayout.NORTH);
        simulador.agregarObservador(this);
    }
    public JButton getBotonGeneradora() {
        return botonGeneradora;
    }

    public JButton getBotonNave() {
        return botonNave;
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
