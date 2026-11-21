
package sistemaLogin.vistaLogin;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class RegistroVista extends JFrame {

    private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private JPasswordField txtRepetirPassword;
    private JButton btnRegistrarse;
    private JButton btnVolver;

    private final Color FONDO = new Color(18, 24, 38);
    private final Color CAMPO = new Color(30, 41, 59);
    private final Color TEXTO = new Color(226, 232, 240);
    private final Color SECUNDARIO = new Color(148, 163, 184);
    private final Color CELESTE = new Color(14, 165, 233);

    public RegistroVista() {

        setTitle("Sistema - Registro");
        setSize(420, 480);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(FONDO);
        panel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        // Título
        JLabel lblTitulo = new JLabel("CREAR CUENTA");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 25));
        lblTitulo.setForeground(TEXTO);
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblSubtitulo = new JLabel("Registrate para continuar");
        lblSubtitulo.setFont(new Font("Arial", Font.PLAIN, 13));
        lblSubtitulo.setForeground(SECUNDARIO);
        lblSubtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Campos
        txtUsuario = new JTextField();
        txtPassword = new JPasswordField();
        txtRepetirPassword = new JPasswordField();

        panel.add(lblTitulo);
        panel.add(Box.createVerticalStrut(8));
        panel.add(lblSubtitulo);
        panel.add(Box.createVerticalStrut(25));

        panel.add(crearCampo("Usuario", txtUsuario));
        panel.add(Box.createVerticalStrut(14));

        panel.add(crearCampo("Contraseña", txtPassword));
        panel.add(Box.createVerticalStrut(14));

        panel.add(crearCampo("Repetir contraseña", txtRepetirPassword));
        panel.add(Box.createVerticalStrut(23));

        // Botones
        btnRegistrarse = crearBoton("Registrarse", CELESTE);
        btnVolver = crearBoton("Volver al login", new Color(51, 65, 85));

        panel.add(btnRegistrarse);
        panel.add(Box.createVerticalStrut(10));
        panel.add(btnVolver);

        add(panel);
    }

    private JPanel crearCampo(String texto, JComponent campo) {

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(FONDO);

        panel.setPreferredSize(new Dimension(300, 60));
        panel.setMaximumSize(new Dimension(300, 60));
        panel.setMinimumSize(new Dimension(300, 60));
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel label = new JLabel(texto);
        label.setFont(new Font("Arial", Font.PLAIN, 13));
        label.setForeground(SECUNDARIO);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);

        campo.setFont(new Font("Arial", Font.PLAIN, 14));
        campo.setForeground(TEXTO);
        campo.setBackground(CAMPO);
        if (campo instanceof javax.swing.text.JTextComponent) {
            ((javax.swing.text.JTextComponent) campo).setCaretColor(TEXTO);
        }

        campo.setBorder(BorderFactory.createCompoundBorder(
                new RoundedBorder(new Color(51, 65, 85), 12),
                new EmptyBorder(5, 10, 5, 10)
        ));

        campo.setOpaque(false);
        campo.setPreferredSize(new Dimension(280, 32));
        campo.setMaximumSize(new Dimension(300, 32));
        campo.setMinimumSize(new Dimension(280, 32));
        campo.setAlignmentX(Component.LEFT_ALIGNMENT);

        panel.add(label);
        panel.add(Box.createVerticalStrut(7));
        panel.add(campo);

        return panel;
    }

    private JButton crearBoton(String texto, Color color) {

        JButton boton = new RoundedButton(texto, color);

        boton.setFont(new Font("Arial", Font.BOLD, 13));
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setAlignmentX(Component.CENTER_ALIGNMENT);

        boton.setPreferredSize(new Dimension(300, 38));
        boton.setMaximumSize(new Dimension(300, 38));
        boton.setMinimumSize(new Dimension(300, 38));

        return boton;
    }

    private static class RoundedBorder extends javax.swing.border.AbstractBorder {

        private final Color color;
        private final int radio;

        public RoundedBorder(Color color, int radio) {
            this.color = color;
            this.radio = radio;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y,
                                int width, int height) {

            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            g2.setColor(color);
            g2.drawRoundRect(x, y, width - 1, height - 1, radio, radio);

            g2.dispose();
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(6, 10, 6, 10);
        }
    }

    private static class RoundedButton extends JButton {

        private final Color color;

        public RoundedButton(String texto, Color color) {
            super(texto);
            this.color = color;

            setContentAreaFilled(false);
            setBorderPainted(false);
            setOpaque(false);
            setBorder(new EmptyBorder(8, 15, 8, 15));
        }

        @Override
        protected void paintComponent(Graphics g) {

            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            g2.setColor(getModel().isRollover()
                    ? color.brighter() : color);

            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 14, 14);
            g2.dispose();

            super.paintComponent(g);
        }
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
