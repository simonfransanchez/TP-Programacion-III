
package vista;

import tripulacion.Tripulante;
import tripulacion.Cargo;
import tripulacion.Origen;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class CrearTripulanteVista extends JFrame {

    // COLORES
    private final Color FONDO = new Color(15, 23, 42);
    private final Color TARJETA = new Color(30, 41, 59);
    private final Color CAMPO = new Color(15, 23, 42);
    private final Color TEXTO = new Color(226, 232, 240);
    private final Color SECUNDARIO = new Color(148, 163, 184);
    private final Color CELESTE = new Color(14, 165, 233);
    private final Color BORDE = new Color(51, 65, 85);
    private final Color ROJO = new Color(220, 70, 70);
    private final Color VERDE = new Color(16, 185, 129);

    // CAMPOS
    private JTextField txtIdentidad;
    private JTextField txtAntiguedad;
    private JTextField txtConsejos;

    // OPCIONES
    private ButtonGroup grupoCargo;
    private ButtonGroup grupoOrigen;

    private JRadioButton[] opcionesCargo;
    private JRadioButton[] opcionesOrigen;

    // BOTONES
    private JButton btnAgregar;
    private JButton btnEliminar;
    private JButton btnFinalizar;

    // LISTA
    private DefaultListModel<Tripulante> modeloLista;
    private JList<Tripulante> listaTripulantes;

    // PANELES
    private JPanel formulario;
    private JPanel panelConsejos;

    public CrearTripulanteVista() {

        setTitle("Configuración de tripulación");
        setSize(1050, 750);
        setMinimumSize(new Dimension(900, 650));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(20, 20));
        getContentPane().setBackground(FONDO);

        // HEADER
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        header.setBackground(FONDO);
        header.setBorder(new EmptyBorder(25, 30, 5, 30));

        JLabel titulo = new JLabel("GESTIÓN DE TRIPULACIÓN");
        titulo.setFont(new Font("Arial", Font.BOLD, 25));
        titulo.setForeground(TEXTO);

        JLabel subtitulo = new JLabel(
                "Configurá los integrantes que formarán parte de tu nave"
        );
        subtitulo.setFont(new Font("Arial", Font.PLAIN, 14));
        subtitulo.setForeground(SECUNDARIO);

        header.add(titulo);
        header.add(Box.createVerticalStrut(6));
        header.add(subtitulo);

        add(header, BorderLayout.NORTH);

        // CONTENIDO CENTRAL
        JPanel centro = new JPanel(new GridLayout(1, 2, 20, 0));
        centro.setBackground(FONDO);
        centro.setBorder(new EmptyBorder(10, 30, 10, 30));

        centro.add(crearPanelFormulario());
        centro.add(crearPanelLista());

        add(centro, BorderLayout.CENTER);

        // FOOTER
        JPanel footer = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        footer.setBackground(FONDO);
        footer.setBorder(new EmptyBorder(0, 30, 20, 30));

        btnFinalizar = crearBoton(
                "FINALIZAR Y CONTINUAR",
                VERDE
        );

        footer.add(btnFinalizar);

        add(footer, BorderLayout.SOUTH);

        actualizarConsejos();
    }

    // =====================================================
    // PANEL DEL FORMULARIO
    // =====================================================

    private JPanel crearPanelFormulario() {

        RoundedPanel tarjeta = new RoundedPanel(TARJETA, 20);
        tarjeta.setLayout(new BorderLayout(10, 10));
        tarjeta.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("Nuevo tripulante");
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        titulo.setForeground(TEXTO);

        tarjeta.add(titulo, BorderLayout.NORTH);

        // FORMULARIO VERTICAL
        formulario = new JPanel();
        formulario.setLayout(new BoxLayout(formulario, BoxLayout.Y_AXIS));
        formulario.setBackground(TARJETA);
        formulario.setBorder(new EmptyBorder(10, 0, 10, 5));

        // IDENTIDAD
        txtIdentidad = new JTextField();
        configurarCampo(txtIdentidad);

        formulario.add(crearCampoFormulario(
                "IDENTIDAD",
                txtIdentidad
        ));

        formulario.add(Box.createVerticalStrut(15));

        // CARGO
        formulario.add(crearCampoFormulario(
                "CARGO",
                crearOpcionesCargo()
        ));

        formulario.add(Box.createVerticalStrut(15));

        // ORIGEN
        formulario.add(crearCampoFormulario(
                "NACIONALIDAD",
                crearOpcionesOrigen()
        ));

        formulario.add(Box.createVerticalStrut(15));

        // ANTIGÜEDAD
        txtAntiguedad = new JTextField();
        configurarCampo(txtAntiguedad);

        formulario.add(crearCampoFormulario(
                "ANTIGÜEDAD (AÑOS)",
                txtAntiguedad
        ));

        formulario.add(Box.createVerticalStrut(15));

        // CONSEJOS
        txtConsejos = new JTextField();
        configurarCampo(txtConsejos);

        panelConsejos = crearCampoFormulario(
                "CANTIDAD DE CONSEJOS",
                txtConsejos
        );

        formulario.add(panelConsejos);

        // SCROLL PARA EVITAR QUE LOS CAMPOS QUEDEN TAPADOS
        JScrollPane scroll = new JScrollPane(formulario);
        scroll.setBorder(null);
        scroll.setBackground(TARJETA);
        scroll.getViewport().setBackground(TARJETA);
        scroll.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );
        scroll.getVerticalScrollBar().setUnitIncrement(15);

        tarjeta.add(scroll, BorderLayout.CENTER);

        // BOTÓN SIEMPRE DEBAJO DEL FORMULARIO
        JPanel panelBoton = new JPanel(new BorderLayout());
        panelBoton.setBackground(TARJETA);
        panelBoton.setBorder(new EmptyBorder(10, 0, 0, 0));

        btnAgregar = crearBoton(
                "AGREGAR TRIPULANTE",
                CELESTE
        );

        panelBoton.add(btnAgregar, BorderLayout.CENTER);

        tarjeta.add(panelBoton, BorderLayout.SOUTH);

        return tarjeta;
    }

    // =====================================================
    // PANEL DE LA LISTA
    // =====================================================

    private JPanel crearPanelLista() {

        RoundedPanel tarjeta = new RoundedPanel(TARJETA, 20);
        tarjeta.setLayout(new BorderLayout(10, 15));
        tarjeta.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("Tu tripulación");
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        titulo.setForeground(TEXTO);

        JLabel subtitulo = new JLabel(
                "Integrantes incorporados a la nave"
        );
        subtitulo.setFont(new Font("Arial", Font.PLAIN, 12));
        subtitulo.setForeground(SECUNDARIO);

        JPanel encabezado = new JPanel();
        encabezado.setLayout(new BoxLayout(encabezado, BoxLayout.Y_AXIS));
        encabezado.setBackground(TARJETA);

        encabezado.add(titulo);
        encabezado.add(Box.createVerticalStrut(5));
        encabezado.add(subtitulo);

        tarjeta.add(encabezado, BorderLayout.NORTH);

        modeloLista = new DefaultListModel<>();
        listaTripulantes = new JList<>(modeloLista);

        listaTripulantes.setBackground(TARJETA);
        listaTripulantes.setForeground(TEXTO);
        listaTripulantes.setSelectionBackground(
                new Color(51, 65, 85)
        );
        listaTripulantes.setSelectionForeground(TEXTO);
        listaTripulantes.setFixedCellHeight(75);
        listaTripulantes.setCellRenderer(new TripulanteRenderer());

        JScrollPane scroll = new JScrollPane(listaTripulantes);
        scroll.setBorder(null);
        scroll.getViewport().setBackground(TARJETA);

        tarjeta.add(scroll, BorderLayout.CENTER);

        btnEliminar = crearBoton(
                "ELIMINAR SELECCIONADO",
                ROJO
        );

        tarjeta.add(btnEliminar, BorderLayout.SOUTH);

        return tarjeta;
    }

    // =====================================================
    // OPCIONES CIRCULARES DE CARGO
    // =====================================================

    private JPanel crearOpcionesCargo() {

        JPanel panel = new JPanel(
                new FlowLayout(FlowLayout.LEFT, 12, 5)
        );
        panel.setBackground(TARJETA);

        grupoCargo = new ButtonGroup();

        Cargo[] cargos = Cargo.values();
        opcionesCargo = new JRadioButton[cargos.length];

        for (int i = 0; i < cargos.length; i++) {

            opcionesCargo[i] = crearRadio(cargos[i].toString());

            grupoCargo.add(opcionesCargo[i]);
            panel.add(opcionesCargo[i]);

            opcionesCargo[i].addActionListener(
                    e -> actualizarConsejos()
            );
        }

        opcionesCargo[0].setSelected(true);

        return panel;
    }

    // =====================================================
    // OPCIONES CIRCULARES DE ORIGEN
    // =====================================================

    private JPanel crearOpcionesOrigen() {

        JPanel panel = new JPanel(
                new FlowLayout(FlowLayout.LEFT, 12, 5)
        );
        panel.setBackground(TARJETA);

        grupoOrigen = new ButtonGroup();

        Origen[] origenes = Origen.values();
        opcionesOrigen = new JRadioButton[origenes.length];

        for (int i = 0; i < origenes.length; i++) {

            opcionesOrigen[i] = crearRadio(origenes[i].toString());

            grupoOrigen.add(opcionesOrigen[i]);
            panel.add(opcionesOrigen[i]);
        }

        opcionesOrigen[0].setSelected(true);

        return panel;
    }

    private JRadioButton crearRadio(String texto) {

        JRadioButton radio = new JRadioButton(texto);

        radio.setForeground(TEXTO);
        radio.setBackground(TARJETA);
        radio.setFont(new Font("Arial", Font.PLAIN, 12));
        radio.setFocusPainted(false);
        radio.setOpaque(false);
        radio.setCursor(new Cursor(Cursor.HAND_CURSOR));

        return radio;
    }

    // =====================================================
    // CAMPOS DEL FORMULARIO
    // =====================================================

    private JPanel crearCampoFormulario(
            String titulo,
            JComponent componente
    ) {

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(TARJETA);
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel etiqueta = new JLabel(titulo);
        etiqueta.setFont(new Font("Arial", Font.BOLD, 11));
        etiqueta.setForeground(SECUNDARIO);

        componente.setAlignmentX(Component.LEFT_ALIGNMENT);

        panel.add(etiqueta);
        panel.add(Box.createVerticalStrut(7));
        panel.add(componente);

        return panel;
    }

    private void configurarCampo(JTextField campo) {

        campo.setBackground(CAMPO);
        campo.setForeground(TEXTO);
        campo.setCaretColor(TEXTO);
        campo.setFont(new Font("Arial", Font.PLAIN, 14));
        campo.setOpaque(false);

        campo.setBorder(BorderFactory.createCompoundBorder(
                new RoundedBorder(BORDE, 12),
                new EmptyBorder(8, 12, 8, 12)
        ));

        campo.setPreferredSize(new Dimension(200, 40));
        campo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
    }

    // =====================================================
    // BOTONES
    // =====================================================

    private JButton crearBoton(String texto, Color color) {

        JButton boton = new JButton(texto) {

            @Override
            protected void paintComponent(Graphics g) {

                Graphics2D g2 = (Graphics2D) g.create();

                g2.setRenderingHint(
                        RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON
                );

                Color fondo = color;

                if (getModel().isPressed()) {
                    fondo = color.darker().darker();
                } else if (getModel().isRollover()) {
                    fondo = color.brighter();
                }

                g2.setColor(fondo);
                g2.fillRoundRect(
                        0, 0, getWidth(), getHeight(),
                        15, 15
                );

                g2.dispose();

                super.paintComponent(g);
            }
        };

        boton.setForeground(Color.WHITE);
        boton.setFont(new Font("Arial", Font.BOLD, 12));
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setContentAreaFilled(false);
        boton.setOpaque(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setPreferredSize(new Dimension(200, 42));

        return boton;
    }

    // =====================================================
    // ACTUALIZAR CAMPO DE CONSEJOS
    // =====================================================

    private void actualizarConsejos() {

        if (panelConsejos == null) {
            return;
        }

        boolean esConsejero = getCargo() == Cargo.CONSEJERO;

        panelConsejos.setVisible(esConsejero);

        txtConsejos.setEditable(esConsejero);
        txtConsejos.setEnabled(esConsejero);

        formulario.revalidate();
        formulario.repaint();
    }

    // =====================================================
    // MÉTODOS UTILIZADOS POR EL CONTROLADOR
    // =====================================================

    public void agregarATabla(Tripulante tripulante) {
        modeloLista.addElement(tripulante);
    }

    public Tripulante getTripulanteSeleccionado() {
        return listaTripulantes.getSelectedValue();
    }

    public void eliminarDeLista(Tripulante tripulante) {
        modeloLista.removeElement(tripulante);
    }

    public void limpiarCampos() {

        txtIdentidad.setText("");
        txtAntiguedad.setText("");
        txtConsejos.setText("");

        opcionesCargo[0].setSelected(true);
        opcionesOrigen[0].setSelected(true);

        actualizarConsejos();
    }

    public String getIdentidad() {
        return txtIdentidad.getText().trim();
    }

    public String getAntiguedad() {
        return txtAntiguedad.getText().trim();
    }

    public String getConsejos() {
        return txtConsejos.getText().trim();
    }

    public Cargo getCargo() {

        for (int i = 0; i < opcionesCargo.length; i++) {
            if (opcionesCargo[i].isSelected()) {
                return Cargo.values()[i];
            }
        }

        return null;
    }

    public Origen getOrigen() {

        for (int i = 0; i < opcionesOrigen.length; i++) {
            if (opcionesOrigen[i].isSelected()) {
                return Origen.values()[i];
            }
        }

        return null;
    }

    public JButton getBtnAgregar() {
        return btnAgregar;
    }

    public JButton getBtnEliminar() {
        return btnEliminar;
    }

    public JButton getBtnFinalizar() {
        return btnFinalizar;
    }

    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(
                this,
                mensaje,
                "Error",
                JOptionPane.ERROR_MESSAGE
        );
    }

    // =====================================================
    // BORDE REDONDEADO PARA LOS CAMPOS
    // =====================================================

    private class RoundedBorder extends AbstractBorder {

        private final Color color;
        private final int radio;

        public RoundedBorder(Color color, int radio) {
            this.color = color;
            this.radio = radio;
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

            g2.drawRoundRect(
                    x,
                    y,
                    width - 1,
                    height - 1,
                    radio,
                    radio
            );

            g2.dispose();
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(8, 12, 8, 12);
        }
    }

    // =====================================================
    // PANEL CON ESQUINAS REDONDEADAS
    // =====================================================

    private class RoundedPanel extends JPanel {

        private final Color color;
        private final int radio;

        public RoundedPanel(Color color, int radio) {
            this.color = color;
            this.radio = radio;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {

            Graphics2D g2 = (Graphics2D) g.create();

            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );

            g2.setColor(color);

            g2.fillRoundRect(
                    0,
                    0,
                    getWidth(),
                    getHeight(),
                    radio,
                    radio
            );

            g2.dispose();

            super.paintComponent(g);
        }
    }

    // =====================================================
    // RENDERER DE LA LISTA DE TRIPULANTES
    // =====================================================

    private class TripulanteRenderer extends JPanel
            implements ListCellRenderer<Tripulante> {

        private JLabel nombre;
        private JLabel detalles;

        public TripulanteRenderer() {

            setLayout(new BorderLayout(10, 5));
            setBorder(new EmptyBorder(10, 12, 10, 12));
            setOpaque(true);

            nombre = new JLabel();
            nombre.setFont(new Font("Arial", Font.BOLD, 14));
            nombre.setForeground(TEXTO);

            detalles = new JLabel();
            detalles.setFont(new Font("Arial", Font.PLAIN, 11));
            detalles.setForeground(SECUNDARIO);

            JPanel textos = new JPanel();
            textos.setLayout(new BoxLayout(textos, BoxLayout.Y_AXIS));
            textos.setOpaque(false);

            textos.add(nombre);
            textos.add(Box.createVerticalStrut(5));
            textos.add(detalles);

            add(textos, BorderLayout.CENTER);
        }

        @Override
        public Component getListCellRendererComponent(
                JList<? extends Tripulante> lista,
                Tripulante tripulante,
                int index,
                boolean seleccionado,
                boolean tieneFoco
        ) {

            nombre.setText(tripulante.getIdentidad());

            detalles.setText(
                    tripulante.getCargo()
                            + "  •  "
                            + tripulante.getOrigen()
                            + "  •  "
                            + tripulante.getAntiguedad()
                            + " años"
            );

            if (seleccionado) {
                setBackground(new Color(51, 65, 85));
            } else {
                setBackground(TARJETA);
            }

            return this;
        }
    }
}
