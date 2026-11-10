
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
        setSize(400, 330);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JLabel lblTitulo = new JLabel("Iniciar sesión", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        txtUsuario = new JTextField();
        txtPassword = new JPasswordField();

        btnIngresar = new JButton("Ingresar");
        btnRegistrarse = new JButton("Registrarse");

        // Título
        panel.add(lblTitulo);
        panel.add(Box.createVerticalStrut(25));

        // Campos
        panel.add(crearCampo("Usuario:", txtUsuario));
        panel.add(Box.createVerticalStrut(15));

        panel.add(crearCampo("Contraseña:", txtPassword));
        panel.add(Box.createVerticalStrut(25));

        // Botones
        btnIngresar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnRegistrarse.setAlignmentX(Component.CENTER_ALIGNMENT);

        btnIngresar.setMaximumSize(new Dimension(300, 35));
        btnRegistrarse.setMaximumSize(new Dimension(300, 35));

        panel.add(btnIngresar);
        panel.add(Box.createVerticalStrut(10));
        panel.add(btnRegistrarse);

        add(panel);
    }

    private JPanel crearCampo(String texto, JComponent campo) {

        JPanel panel = new JPanel(new BorderLayout(5, 5));

        panel.setMaximumSize(new Dimension(300, 60));
        panel.setPreferredSize(new Dimension(300, 60));

        campo.setPreferredSize(new Dimension(280, 32));

        JLabel label = new JLabel(texto);
        label.setFont(new Font("Arial", Font.PLAIN, 14));

        panel.add(label, BorderLayout.NORTH);
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
