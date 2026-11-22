
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

        setLayout(new GridLayout(3, 1, 0, 12));
        setBackground(EstiloNave.FONDO);
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        agregarRecurso("COMBUSTIBLE", combustible, EstiloNave.AZUL);
        agregarRecurso("ENERGÍA", energia, EstiloNave.VERDE);
        agregarRecurso("DESGASTE", desgaste, EstiloNave.AMARILLO);

        asistente.agregarObservador(this);

        pintar(
                asistente.getCombustible(),
                asistente.getEnergia(),
                asistente.getDesgaste()
        );
    }

    private void agregarRecurso(String nombre, JProgressBar barra, Color color) {
        JPanel contenedor = EstiloNave.panel();
        contenedor.setLayout(new BorderLayout(8, 8));

        JLabel titulo = EstiloNave.titulo(nombre);
        EstiloNave.estilizarBarra(barra, color);

        contenedor.add(titulo, BorderLayout.NORTH);
        contenedor.add(barra, BorderLayout.CENTER);

        add(contenedor);
    }

    @Override
    public void actualizar() {
        final int c = asistente.getCombustible();
        final int e = asistente.getEnergia();
        final int d = asistente.getDesgaste();

        SwingUtilities.invokeLater(() -> pintar(c, e, d));
    }

    private void pintar(int c, int e, int d) {
        combustible.setValue(c);
        combustible.setString(c + "%");

        energia.setValue(e);
        energia.setString(e + "%");

        desgaste.setValue(d);
        desgaste.setString(d + "%");
    }
}
