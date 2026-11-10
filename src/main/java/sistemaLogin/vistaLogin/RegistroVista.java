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
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel(new GridLayout(5, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(25, 40, 25, 40));

        JLabel lblTitulo = new JLabel("Crear cuenta", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));

        txtUsuario = new JTextField();
        txtPassword = new JPasswordField();
        txtRepetirPassword = new JPasswordField();

        btnRegistrarse = new JButton("Registrarse");
        btnVolver = new JButton("Volver al login");

        panel.add(lblTitulo);
        panel.add(crearCampo("Usuario:", txtUsuario));
        panel.add(crearCampo("Contraseña:", txtPassword));
        panel.add(crearCampo("Repetir contraseña:", txtRepetirPassword));
        panel.add(btnRegistrarse);

        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(
                BorderFactory.createEmptyBorder(25, 40, 25, 40)
        );

        panelPrincipal.add(panel, BorderLayout.CENTER);
        panelPrincipal.add(btnVolver, BorderLayout.SOUTH);

        add(panelPrincipal);
    }

    private JPanel crearCampo(String texto, JComponent campo) {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.add(new JLabel(texto), BorderLayout.NORTH);
        panel.add(campo, BorderLayout.CENTER);
        return panel;
    }

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
