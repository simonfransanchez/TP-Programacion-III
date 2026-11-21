package vista.paneles;

import universoParalelo.SimuladorRecondito;
import java.awt.event.ActionListener;
import destinoytablero.Observador;
import javax.swing.*;
import java.awt.*;

public class PanelVisor extends JPanel implements Observador {

    private final SimuladorRecondito simulador;
    private final JTextArea consola = new JTextArea();
    private int eventosMostrados = 0;
    private final JButton btnGeneradora = new JButton("Crear Generadora");
    private final JButton btnNave = new JButton("Crear Nave Consumidora");
    private final JButton btnDetener = new JButton("Detener simulación");
    public PanelVisor(SimuladorRecondito simulador) {
        this.simulador = simulador;

        setLayout(new BorderLayout());
        consola.setEditable(false);
        add(new JScrollPane(consola), BorderLayout.CENTER);
        simulador.agregarObservador(this);
        actualizar();
        JPanel panelBotones = new JPanel();
        panelBotones.add(btnGeneradora);
        panelBotones.add(btnNave);
        add(panelBotones, BorderLayout.SOUTH);

        panelBotones.add(btnDetener);
    }

    @Override
    public void actualizar() {
        SwingUtilities.invokeLater(() -> {

            var nuevos = simulador.getEventosDesde(eventosMostrados);

            for (String evento : nuevos) {
                consola.append(evento + "\n");
            }

            eventosMostrados += nuevos.size();
        });
    }
    
    public void agregarListenerGeneradora(ActionListener listener) {
        btnGeneradora.addActionListener(listener);
    }

    public void agregarListenerNave(ActionListener listener) {
        btnNave.addActionListener(listener);
    }
    public void agregarListenerDetener(ActionListener listener) {
        btnDetener.addActionListener(listener);
    }

    public void deshabilitarBotones() {
        btnGeneradora.setEnabled(false);
        btnNave.setEnabled(false);
        btnDetener.setEnabled(false);
    }
}