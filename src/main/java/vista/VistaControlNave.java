package vista;

import asistente.Asistente;
import destinoytablero.Tablero;
import misiones.Informe;
import vista.paneles.PanelBitacora;
import vista.paneles.PanelInformes;
import vista.paneles.PanelRecursos;
import vista.paneles.PanelTablero;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class VistaControlNave extends JPanel {
    private final JComboBox<String> misiones = new JComboBox<>(new String[]{
            "M-01 Intercepción y asistencia", "M-02 Recolección", "M-03 Retorno seguro"});
    private final JButton iniciar = new JButton("Iniciar misión");

    public VistaControlNave(Asistente asistente, Tablero tablero) {
        setLayout(new BorderLayout());
        JPanel izquierda = new JPanel(new BorderLayout());
        izquierda.add(new PanelRecursos(asistente), BorderLayout.NORTH);
        //izquierda.add(new PanelBitacora(asistente), BorderLayout.CENTER);
        //izquierda.add(new PanelInformes(asistente), BorderLayout.SOUTH);
        JPanel controles = new JPanel();
        controles.add(misiones);
        controles.add(iniciar);
        add(izquierda, BorderLayout.WEST);
        add(new PanelTablero(tablero), BorderLayout.CENTER);
        add(controles, BorderLayout.SOUTH);
    }

    public void setControlador(ActionListener c) { iniciar.addActionListener(c); }
    public int getNumeroMision() { return misiones.getSelectedIndex() + 1; }
    public void habilitarInicio(boolean b) { iniciar.setEnabled(b); misiones.setEnabled(b); }
    public void mostrarResultado(Informe i) { JOptionPane.showMessageDialog(this, i.toString()); }
    public void mostrarError(String m) { JOptionPane.showMessageDialog(this, m, "Error", JOptionPane.ERROR_MESSAGE); }
}