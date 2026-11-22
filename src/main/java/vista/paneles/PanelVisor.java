
package vista.paneles;

import universoParalelo.SimuladorRecondito;
import destinoytablero.Observador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PanelVisor extends JPanel implements Observador {

    private final SimuladorRecondito simulador;
    private final JTextArea consola = new JTextArea();
    private int eventosMostrados = 0;

    private final JButton btnGeneradora =
            EstiloNave.boton("Crear Generadora", EstiloNave.VERDE);

    private final JButton btnNave =
            EstiloNave.boton("Crear Nave Consumidora", EstiloNave.AZUL);

    private final JButton btnDetener =
            EstiloNave.boton("Detener simulación", EstiloNave.ROJO);

    public PanelVisor(SimuladorRecondito simulador) {
        this.simulador = simulador;

        setLayout(new BorderLayout(10, 10));
        setBackground(EstiloNave.FONDO);
        setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        consola.setEditable(false);
        EstiloNave.estilizarArea(consola);

        JPanel encabezado = EstiloNave.panel();
        encabezado.setLayout(new BorderLayout());
        encabezado.add(EstiloNave.titulo("SIMULACIÓN DEL UNIVERSO"),
                BorderLayout.WEST);

        add(encabezado, BorderLayout.NORTH);
        add(EstiloNave.scroll(consola), BorderLayout.CENTER);

        JPanel panelBotones = EstiloNave.panel();
        panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 12, 8));

        panelBotones.add(btnGeneradora);
        panelBotones.add(btnNave);
        panelBotones.add(btnDetener);

        add(panelBotones, BorderLayout.SOUTH);

        simulador.agregarObservador(this);
        actualizar();
    }

    @Override
    public void actualizar() {
        SwingUtilities.invokeLater(() -> {
            var nuevos = simulador.getEventosDesde(eventosMostrados);

            for (String evento : nuevos) {
                consola.append("> " + evento + "\n\n");
            }

            eventosMostrados += nuevos.size();
            consola.setCaretPosition(consola.getDocument().getLength());
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
