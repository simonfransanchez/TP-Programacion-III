package naves.vistaNaves;
import javax.swing.*;
import java.awt.*;

public class CrearNaveVista extends JFrame {

    private JComboBox<String> cmbTipoNave;
    private JButton btnCrear;

    public CrearNaveVista() {

        setTitle("Sistema - Crear nave");
        setSize(420, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(25, 40, 25, 40));

        JLabel lblTitulo = new JLabel("Crear una nave");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblTipo = new JLabel("Seleccione el tipo de nave:");
        lblTipo.setFont(new Font("Arial", Font.PLAIN, 14));
        lblTipo.setAlignmentX(Component.CENTER_ALIGNMENT);

        cmbTipoNave = new JComboBox<>(new String[]{
                "Carguero",
                "Exploradora",
                "Combate"
        });

        cmbTipoNave.setMaximumSize(new Dimension(300, 35));
        cmbTipoNave.setPreferredSize(new Dimension(300, 35));

        btnCrear = new JButton("Crear nave");
        btnCrear.setMaximumSize(new Dimension(300, 35));
        btnCrear.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(lblTitulo);
        panel.add(Box.createVerticalStrut(30));
        panel.add(lblTipo);
        panel.add(Box.createVerticalStrut(10));
        panel.add(cmbTipoNave);
        panel.add(Box.createVerticalStrut(30));
        panel.add(btnCrear);

        add(panel);
    }

    public String getTipoNave() {
        return (String) cmbTipoNave.getSelectedItem();
    }

    public JButton getBtnCrear() {
        return btnCrear;
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
}
