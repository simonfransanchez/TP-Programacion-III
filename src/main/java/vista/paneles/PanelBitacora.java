
package vista.paneles;

import asistente.Asistente;
import destinoytablero.Observador;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PanelBitacora extends JPanel implements Observador {

    private final Asistente asistente;
    private final JTextArea texto = new JTextArea(10, 30);

    public PanelBitacora(Asistente asistente) {
        this.asistente = asistente;

        setLayout(new BorderLayout());
        setBackground(EstiloNave.FONDO);
        setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        texto.setEditable(false);
        EstiloNave.estilizarArea(texto);

        JPanel encabezado = new JPanel(new BorderLayout());
        encabezado.setBackground(EstiloNave.PANEL);
        encabezado.setBorder(BorderFactory.createEmptyBorder(5, 8, 10, 8));

        encabezado.add(EstiloNave.titulo("REGISTRO DE OPERACIONES"), BorderLayout.WEST);

        add(encabezado, BorderLayout.NORTH);
        add(EstiloNave.scroll(texto), BorderLayout.CENTER);

        pintar(asistente.getEventos());
        asistente.agregarObservadorBitacora(this);
    }

    @Override
    public void actualizar() {
        final List<String> eventos = asistente.getEventos();
        SwingUtilities.invokeLater(() -> pintar(eventos));
    }

    private void pintar(List<String> eventos) {
        texto.setText(String.join("\n\n", eventos));
        texto.setCaretPosition(texto.getDocument().getLength());
    }
}
