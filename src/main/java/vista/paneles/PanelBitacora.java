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
        texto.setEditable(false);
        texto.setLineWrap(true);
        texto.setWrapStyleWord(true);
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Bitácora"));
        add(new JScrollPane(texto), BorderLayout.CENTER);

        pintar(asistente.getEventos());
        asistente.agregarObservadorBitacora(this);   // se registra antes de iniciar cualquier misión
    }

    @Override
    public void actualizar() {
        // Se ejecuta en el hilo que agregó el evento: la copia se toma acá y se dibuja en el hilo de Swing
        final List<String> eventos = asistente.getEventos();
        SwingUtilities.invokeLater(() -> pintar(eventos));
    }

    private void pintar(List<String> eventos) {
        texto.setText(String.join("\n", eventos));
        texto.setCaretPosition(texto.getDocument().getLength());   // baja hasta el último evento
    }
}
