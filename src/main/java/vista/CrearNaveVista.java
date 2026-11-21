
package vista;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import java.awt.*;

public class CrearNaveVista extends JFrame {

    private JButton btnCrear;

    private String tipoNaveSeleccionada = null;

    private JPanel cardCarguero;
    private JPanel cardExploradora;
    private JPanel cardCombate;

    private JPanel tarjetas;

    private final Color FONDO = new Color(18, 24, 38);
    private final Color FONDO_TARJETA = new Color(30, 41, 59);
    private final Color COLOR_SELECCION = new Color(56, 189, 248);
    private final Color TEXTO = new Color(226, 232, 240);
    private final Color TEXTO_SECUNDARIO = new Color(148, 163, 184);

    public CrearNaveVista() {

        setTitle("Crear nave");
        setSize(760, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        panelPrincipal.setBackground(FONDO);
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(30, 35, 30, 35));

        // Título
        JLabel lblTitulo = new JLabel("SELECCIÓN DE NAVE");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 25));
        lblTitulo.setForeground(TEXTO);
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblSubtitulo = new JLabel("Elegí tu nave para continuar");
        lblSubtitulo.setFont(new Font("Arial", Font.PLAIN, 14));
        lblSubtitulo.setForeground(TEXTO_SECUNDARIO);
        lblSubtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Tarjetas
        cardCarguero = crearTarjeta(
                "Carguero",
                "src/main/java/imagenes/nave_carga.png"
        );

        cardExploradora = crearTarjeta(
                "Exploradora",
                "src/main/java/imagenes/nave_exploracion.png"
        );

        cardCombate = crearTarjeta(
                "Combate",
                "src/main/java/imagenes/nave_ataque.png"
        );

        tarjetas = new JPanel(new GridLayout(1, 3, 20, 0));
        tarjetas.setBackground(FONDO);
        tarjetas.setMaximumSize(new Dimension(680, 250));

        tarjetas.add(cardCarguero);
        tarjetas.add(cardExploradora);
        tarjetas.add(cardCombate);

        // Eventos de selección
        cardCarguero.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                seleccionarNave("Carguero", cardCarguero);
            }
        });

        cardExploradora.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                seleccionarNave("Exploradora", cardExploradora);
            }
        });

        cardCombate.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                seleccionarNave("Combate", cardCombate);
            }
        });

        // Botón crear redondeado
        btnCrear = new JButton("CREAR NAVE") {

            @Override
            protected void paintComponent(Graphics g) {

                Graphics2D g2 = (Graphics2D) g.create();

                g2.setRenderingHint(
                        RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON
                );

                if (getModel().isPressed()) {
                    g2.setColor(new Color(2, 132, 199));
                } else if (getModel().isRollover()) {
                    g2.setColor(new Color(56, 189, 248));
                } else {
                    g2.setColor(new Color(14, 165, 233));
                }

                g2.fillRoundRect(
                        0, 0, getWidth(), getHeight(), 18, 18
                );

                g2.dispose();

                super.paintComponent(g);
            }
        };

        btnCrear.setFont(new Font("Arial", Font.BOLD, 14));
        btnCrear.setForeground(Color.WHITE);
        btnCrear.setOpaque(false);
        btnCrear.setContentAreaFilled(false);
        btnCrear.setBorderPainted(false);
        btnCrear.setFocusPainted(false);
        btnCrear.setBorder(BorderFactory.createEmptyBorder(12, 30, 12, 30));
        btnCrear.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCrear.setAlignmentX(Component.CENTER_ALIGNMENT);

        btnCrear.setMaximumSize(new Dimension(250, 45));
        btnCrear.setPreferredSize(new Dimension(250, 45));

        // Organización
        panelPrincipal.add(lblTitulo);
        panelPrincipal.add(Box.createVerticalStrut(8));
        panelPrincipal.add(lblSubtitulo);
        panelPrincipal.add(Box.createVerticalStrut(35));
        panelPrincipal.add(tarjetas);
        panelPrincipal.add(Box.createVerticalStrut(35));
        panelPrincipal.add(btnCrear);

        add(panelPrincipal);
    }

    private JPanel crearTarjeta(String nombre, String rutaImagen) {

        JPanel tarjeta = new JPanel();
        tarjeta.setLayout(new BoxLayout(tarjeta, BoxLayout.Y_AXIS));
        tarjeta.setBackground(FONDO_TARJETA);

        tarjeta.setBorder(BorderFactory.createCompoundBorder(
                new BordeRedondeado(new Color(51, 65, 85), 2),
                BorderFactory.createEmptyBorder(12, 12, 12, 12)
        ));

        tarjeta.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Imagen
        JLabel lblImagen = new JLabel();
        lblImagen.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblImagen.setPreferredSize(new Dimension(180, 170));
        lblImagen.setMinimumSize(new Dimension(180, 170));
        lblImagen.setMaximumSize(new Dimension(180, 170));

        ImageIcon icono = new ImageIcon(rutaImagen);

        if (icono.getIconWidth() > 0) {

            Image imagen = icono.getImage().getScaledInstance(
                    180, 170, Image.SCALE_SMOOTH
            );

            lblImagen.setIcon(new ImageIcon(imagen));

        } else {

            lblImagen.setText("Imagen no encontrada");
            lblImagen.setForeground(TEXTO_SECUNDARIO);
        }

        // Nombre
        JLabel lblNombre = new JLabel(nombre);
        lblNombre.setFont(new Font("Arial", Font.BOLD, 17));
        lblNombre.setForeground(TEXTO);
        lblNombre.setAlignmentX(Component.CENTER_ALIGNMENT);

        tarjeta.add(lblImagen);
        tarjeta.add(Box.createVerticalStrut(12));
        tarjeta.add(lblNombre);

        // Seleccionar al tocar la imagen
        lblImagen.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                seleccionarNave(nombre, tarjeta);
            }
        });

        // Seleccionar al tocar el nombre
        lblNombre.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                seleccionarNave(nombre, tarjeta);
            }
        });

        return tarjeta;
    }

    private void seleccionarNave(String tipo, JPanel tarjetaSeleccionada) {

        tipoNaveSeleccionada = tipo;

        // Restaurar bordes
        for (JPanel tarjeta : new JPanel[]{
                cardCarguero, cardExploradora, cardCombate
        }) {

            tarjeta.setBorder(BorderFactory.createCompoundBorder(
                    new BordeRedondeado(new Color(51, 65, 85), 2),
                    BorderFactory.createEmptyBorder(12, 12, 12, 12)
            ));
        }

        // Resaltar tarjeta seleccionada
        tarjetaSeleccionada.setBorder(BorderFactory.createCompoundBorder(
                new BordeRedondeado(COLOR_SELECCION, 3),
                BorderFactory.createEmptyBorder(11, 11, 11, 11)
        ));
    }

    // Borde personalizado con esquinas redondeadas
    private class BordeRedondeado extends AbstractBorder {

        private Color color;
        private int grosor;

        public BordeRedondeado(Color color, int grosor) {
            this.color = color;
            this.grosor = grosor;
        }

        @Override
        public void paintBorder(
                Component c,
                Graphics g,
                int x,
                int y,
                int width,
                int height
        ) {

            Graphics2D g2 = (Graphics2D) g.create();

            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );

            g2.setColor(color);
            g2.setStroke(new BasicStroke(grosor));

            int ajuste = grosor / 2;

            g2.drawRoundRect(
                    x + ajuste,
                    y + ajuste,
                    width - grosor,
                    height - grosor,
                    22,
                    22
            );

            g2.dispose();
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(12, 12, 12, 12);
        }
    }

    // Getters
    public String getTipoNave() {
        return tipoNaveSeleccionada;
    }

    public JButton getBtnCrear() {
        return btnCrear;
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
}
