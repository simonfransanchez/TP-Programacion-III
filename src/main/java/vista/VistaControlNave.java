
package vista;

import asistente.Asistente;
import destinoytablero.Tablero;
import misiones.Informe;
import vista.paneles.PanelBitacora;
import vista.paneles.PanelInformes;
import vista.paneles.PanelRecursos;
import vista.paneles.PanelTablero;
import vista.paneles.EstiloNave;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class VistaControlNave extends JPanel {

    private final JComboBox<String> misiones = new JComboBox<>(new String[]{
            "M-01  Intercepción y asistencia",
            "M-02  Recolección",
            "M-03  Retorno seguro"
    });

    private final JButton iniciar =
            EstiloNave.boton("▶  Iniciar misión", EstiloNave.AZUL);

    private final JButton verTripulacion =
            EstiloNave.boton("☰  Tripulación", EstiloNave.PANEL_SECUNDARIO);

    public VistaControlNave(Asistente asistente, Tablero tablero) {

        setLayout(new BorderLayout(12, 12));
        setBackground(EstiloNave.FONDO);
        setBorder(new EmptyBorder(12, 12, 12, 12));

        // ---------------- ENCABEZADO ----------------

        JPanel encabezado = new JPanel(new BorderLayout());
        encabezado.setBackground(EstiloNave.FONDO);
        encabezado.setBorder(new EmptyBorder(5, 5, 10, 5));

        JLabel titulo = new JLabel("CENTRO DE CONTROL");
        titulo.setForeground(EstiloNave.AZUL);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 22));

        JLabel subtitulo = new JLabel("SISTEMA DE NAVEGACIÓN Y MISIONES");
        subtitulo.setForeground(EstiloNave.TEXTO_SECUNDARIO);
        subtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        JPanel textos = new JPanel();
        textos.setLayout(new BoxLayout(textos, BoxLayout.Y_AXIS));
        textos.setBackground(EstiloNave.FONDO);

        textos.add(titulo);
        textos.add(Box.createVerticalStrut(4));
        textos.add(subtitulo);

        encabezado.add(textos, BorderLayout.WEST);

        add(encabezado, BorderLayout.NORTH);

        // ---------------- PANEL IZQUIERDO ----------------

        JPanel izquierda = new JPanel(new BorderLayout(10, 10));
        izquierda.setBackground(EstiloNave.FONDO);

        izquierda.add(new PanelRecursos(asistente), BorderLayout.NORTH);

        JTabbedPane registros = new JTabbedPane();

        registros.setBackground(EstiloNave.PANEL);
        registros.setForeground(EstiloNave.TEXTO);
        registros.setFont(new Font("Segoe UI", Font.BOLD, 12));

        registros.addTab("Bitácora", new PanelBitacora(asistente));
        registros.addTab("Informes", new PanelInformes(asistente));

        izquierda.add(registros, BorderLayout.CENTER);
        izquierda.setPreferredSize(new Dimension(340, 0));

        // ---------------- PANEL CENTRAL ----------------

        JPanel central = new JPanel(new BorderLayout(10, 10));
        central.setBackground(EstiloNave.FONDO);

        JPanel tableroContenedor = EstiloNave.panel();
        tableroContenedor.setLayout(new BorderLayout(8, 8));

        JLabel tituloTablero = EstiloNave.titulo("MAPA ESPACIAL");
        tituloTablero.setBorder(new EmptyBorder(4, 4, 8, 4));

        tableroContenedor.add(tituloTablero, BorderLayout.NORTH);
        tableroContenedor.add(new PanelTablero(tablero), BorderLayout.CENTER);

        central.add(tableroContenedor, BorderLayout.CENTER);

        // ---------------- CONTROLES INFERIORES ----------------

        JPanel controles = EstiloNave.panel();
        controles.setLayout(new FlowLayout(FlowLayout.CENTER, 12, 10));

        misiones.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        misiones.setBackground(EstiloNave.PANEL_SECUNDARIO);
        misiones.setForeground(EstiloNave.TEXTO);
        misiones.setPreferredSize(new Dimension(260, 40));

        controles.add(misiones);
        controles.add(iniciar);
        controles.add(verTripulacion);

        // ---------------- DISTRIBUCIÓN ----------------

        add(izquierda, BorderLayout.WEST);
        add(central, BorderLayout.CENTER);
        add(controles, BorderLayout.SOUTH);
    }

    public void setControlador(ActionListener c) {
        iniciar.addActionListener(c);
    }

    public JButton getBtnVerTripulacion() {
        return verTripulacion;
    }

    public int getNumeroMision() {
        return misiones.getSelectedIndex() + 1;
    }

    public void habilitarInicio(boolean b) {
        iniciar.setEnabled(b);
        misiones.setEnabled(b);
    }

    public void mostrarResultado(Informe i) {
        JOptionPane.showMessageDialog(this, i.toString());
    }

    public void mostrarError(String m) {
        JOptionPane.showMessageDialog(
                this,
                m,
                "Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
}
