
package sistemaLogin.vistaLogin;

import javax.swing.*;
import java.awt.*;

public class RegistroVista extends JFrame {

    private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private JPasswordField txtRepetirPassword;
    private JButton btnRegistrarse;
    private JButton btnVolver;

    public RegistroVista() {

        setTitle("Sistema - Registro");
        setSize(420, 420);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Panel principal
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        // Título
        JLabel lblTitulo = new JLabel("Crear cuenta");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Campos de texto
        txtUsuario = new JTextField();
        txtPassword = new JPasswordField();
        txtRepetirPassword = new JPasswordField();

        // Botones
        btnRegistrarse = new JButton("Registrarse");
        btnVolver = new JButton("Volver al login");

        // Título
        panel.add(lblTitulo);
        panel.add(Box.createVerticalStrut(20));

        // Usuario
        panel.add(crearCampo("Usuario:", txtUsuario));
        panel.add(Box.createVerticalStrut(12));

        // Contraseña
        panel.add(crearCampo("Contraseña:", txtPassword));
        panel.add(Box.createVerticalStrut(12));

        // Repetir contraseña
        panel.add(crearCampo("Repetir contraseña:", txtRepetirPassword));
        panel.add(Box.createVerticalStrut(20));

        // Configuración de botones
        configurarBoton(btnRegistrarse);
        configurarBoton(btnVolver);

        panel.add(btnRegistrarse);
        panel.add(Box.createVerticalStrut(10));
        panel.add(btnVolver);

        add(panel);
    }

    private JPanel crearCampo(String texto, JComponent campo) {

        JPanel panel = new JPanel(new BorderLayout(5, 5));

        panel.setPreferredSize(new Dimension(320, 62));
        panel.setMaximumSize(new Dimension(320, 62));
        panel.setMinimumSize(new Dimension(320, 62));

        JLabel label = new JLabel(texto);
        label.setFont(new Font("Arial", Font.PLAIN, 14));

        campo.setPreferredSize(new Dimension(300, 32));

        panel.add(label, BorderLayout.NORTH);
        panel.add(campo, BorderLayout.CENTER);

        panel.setAlignmentX(Component.CENTER_ALIGNMENT);

        return panel;
    }

    private void configurarBoton(JButton boton) {

        boton.setPreferredSize(new Dimension(300, 32));
        boton.setMaximumSize(new Dimension(300, 32));
        boton.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    // Métodos para el controlador

    public String getUsuario() {
        return txtUsuario.getText();
    }

    public char[] getPassword() {
        return txtPassword.getPassword();
    }

    public char[] getRepetirPassword() {
        return txtRepetirPassword.getPassword();
    }

    public JButton getBtnRegistrarse() {
        return btnRegistrarse;
    }

    public JButton getBtnVolver() {
        return btnVolver;
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public void limpiarCampos() {
        txtUsuario.setText("");
        txtPassword.setText("");
        txtRepetirPassword.setText("");
    }
}
