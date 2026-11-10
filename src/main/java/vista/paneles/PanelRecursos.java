package vista.paneles;

import asistente.Asistente;
import destinoytablero.Observador;

import javax.swing.*;
import java.awt.*;

public class PanelRecursos extends JPanel implements Observador {
    private final Asistente asistente;
    private final JProgressBar combustible = new JProgressBar(0, 100);
    private final JProgressBar energia = new JProgressBar(0, 100);
    private final JProgressBar desgaste = new JProgressBar(0, 100);

    public PanelRecursos(Asistente asistente) {
        this.asistente = asistente;
        setLayout(new GridLayout(3, 2));
        for (JProgressBar b : new JProgressBar[]{combustible, energia, desgaste}) b.setStringPainted(true);
        add(new JLabel("Combustible")); add(combustible);
        add(new JLabel("Energía"));     add(energia);
        add(new JLabel("Desgaste"));    add(desgaste);
        asistente.agregarObservador(this);
        pintar(asistente.getCombustible(), asistente.getEnergia(), asistente.getDesgaste());
    }

    @Override
    public void actualizar() {
        final int c = asistente.getCombustible(), e = asistente.getEnergia(), d = asistente.getDesgaste();
        SwingUtilities.invokeLater(() -> pintar(c, e, d));
    }

    private void pintar(int c, int e, int d) {
        combustible.setValue(c); energia.setValue(e); desgaste.setValue(d);
    }
}
