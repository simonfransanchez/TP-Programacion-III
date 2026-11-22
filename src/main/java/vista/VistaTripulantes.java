package vista;

import javax.swing.*;
import java.awt.*;

public class VistaTripulantes extends JFrame {

    private JTextArea areaTripulantes;
    private JLabel lblTotal;
    private JButton btnCerrar;

    public VistaTripulantes() {
        setTitle("Tripulación de la nave");
        setSize(700, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("TRIPULANTES Y SALARIOS");
        add(titulo, BorderLayout.NORTH);

        areaTripulantes = new JTextArea();
        areaTripulantes.setEditable(false);
        add(new JScrollPane(areaTripulantes), BorderLayout.CENTER);

        JPanel abajo = new JPanel();
        lblTotal = new JLabel("Total de salarios: 0");
        btnCerrar = new JButton("Cerrar");
        abajo.add(lblTotal);
        abajo.add(btnCerrar);
        add(abajo, BorderLayout.SOUTH);
    }

    // Métodos que usa el controlador

    public void limpiar() {
        areaTripulantes.setText("");
    }

    public void agregarLinea(String texto) {
        areaTripulantes.append(texto + "\n");
    }

    public void mostrarTotal(double total) {
        lblTotal.setText("Total de salarios: " + total);
    }

    public JButton getBtnCerrar() {
        return btnCerrar;
    }
}