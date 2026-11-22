
package vista;

import vista.paneles.EstiloNave;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class VistaTripulantes extends JFrame {

    private JTextArea areaTripulantes;
    private JLabel lblTotal;
    private JButton btnCerrar;

    public VistaTripulantes() {

        setTitle("Tripulación de la nave");
        setSize(700, 450);
        setMinimumSize(new Dimension(550, 350));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        getContentPane().setBackground(EstiloNave.FONDO);
        setLayout(new BorderLayout(12, 12));

        // ---------------- ENCABEZADO ----------------

        JPanel encabezado = new JPanel();
        encabezado.setLayout(new BoxLayout(encabezado, BoxLayout.Y_AXIS));
        encabezado.setBackground(EstiloNave.FONDO);
        encabezado.setBorder(new EmptyBorder(20, 22, 10, 22));

        JLabel titulo = new JLabel("TRIPULACIÓN");
        titulo.setForeground(EstiloNave.AZUL);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 23));

        JLabel subtitulo = new JLabel("PERSONAL ASIGNADO Y COSTOS OPERATIVOS");
        subtitulo.setForeground(EstiloNave.TEXTO_SECUNDARIO);
        subtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        encabezado.add(titulo);
        encabezado.add(Box.createVerticalStrut(5));
        encabezado.add(subtitulo);

        add(encabezado, BorderLayout.NORTH);

        // ---------------- CONTENIDO ----------------

        JPanel contenido = EstiloNave.panel();
        contenido.setLayout(new BorderLayout(10, 10));
        contenido.setBorder(new EmptyBorder(12, 12, 12, 12));

        areaTripulantes = new JTextArea();
        areaTripulantes.setEditable(false);
        EstiloNave.estilizarArea(areaTripulantes);

        contenido.add(
                EstiloNave.scroll(areaTripulantes),
                BorderLayout.CENTER
        );

        JPanel contenedorContenido = new JPanel(new BorderLayout());
        contenedorContenido.setBackground(EstiloNave.FONDO);
        contenedorContenido.setBorder(new EmptyBorder(0, 22, 10, 22));
        contenedorContenido.add(contenido, BorderLayout.CENTER);

        add(contenedorContenido, BorderLayout.CENTER);

        // ---------------- PIE ----------------

        JPanel abajo = EstiloNave.panel();
        abajo.setLayout(new BorderLayout(15, 10));
        abajo.setBorder(new EmptyBorder(12, 18, 12, 18));

        lblTotal = new JLabel("TOTAL DE SALARIOS: 0");
        lblTotal.setForeground(EstiloNave.VERDE);
        lblTotal.setFont(new Font("Segoe UI", Font.BOLD, 15));

        btnCerrar = EstiloNave.boton("Cerrar", EstiloNave.PANEL_SECUNDARIO);

        JPanel contenedorBoton = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        contenedorBoton.setBackground(EstiloNave.PANEL);
        contenedorBoton.add(btnCerrar);

        abajo.add(lblTotal, BorderLayout.CENTER);
        abajo.add(contenedorBoton, BorderLayout.EAST);

        JPanel pie = new JPanel(new BorderLayout());
        pie.setBackground(EstiloNave.FONDO);
        pie.setBorder(new EmptyBorder(0, 22, 18, 22));
        pie.add(abajo, BorderLayout.CENTER);

        add(pie, BorderLayout.SOUTH);
    }

    // ---------------- MÉTODOS DEL CONTROLADOR ----------------

    public void limpiar() {
        areaTripulantes.setText("");
    }

    public void agregarLinea(String texto) {
        areaTripulantes.append(texto + "\n");
    }

    public void mostrarTotal(double total) {
        lblTotal.setText("TOTAL DE SALARIOS: " + total);
    }

    public JButton getBtnCerrar() {
        return btnCerrar;
    }
}
