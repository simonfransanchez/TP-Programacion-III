package sistemaLogin.vistaLogin;

import javax.swing.*;
import java.awt.*;

public class LoginVista extends JFrame {

    private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private JButton btnIngresar;
    private JButton btnRegistrarse;

    public LoginVista() {

        setTitle("Sistema - Iniciar sesión");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel(new GridLayout(5, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(25, 40, 25, 40));

        JLabel lblTitulo = new JLabel("Iniciar sesión", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));

        txtUsuario = new JTextField();
        txtPassword = new JPasswordField();

        btnIngresar = new JButton("Ingresar");
        btnRegistrarse = new JButton("Registrarse");

        panel.add(lblTitulo);
        panel.add(crearCampo("Usuario:", txtUsuario));
        panel.add(crearCampo("Contraseña:", txtPassword));
        panel.add(btnIngresar);
        panel.add(btnRegistrarse);

        add(panel);
    }

    private JPanel crearCampo(String texto, JComponent campo) {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.add(new JLabel(texto), BorderLayout.NORTH);
        panel.add(campo, BorderLayout.CENTER);
        return panel;
    }

    // Métodos para que el controlador pueda acceder a los componentes

    public String getUsuario() {
        return txtUsuario.getText();
    }

    public char[] getPassword() {
        return txtPassword.getPassword();
    }

    public JButton getBtnIngresar() {
        return btnIngresar;
    }

    public JButton getBtnRegistrarse() {
        return btnRegistrarse;
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public void limpiarCampos() {
        txtUsuario.setText("");
        txtPassword.setText("");
    }
}