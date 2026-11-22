
package vista.paneles;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class EstiloNave {

    public static final Color FONDO = new Color(13, 18, 30);
    public static final Color PANEL = new Color(22, 30, 45);
    public static final Color PANEL_SECUNDARIO = new Color(29, 39, 56);
    public static final Color AZUL = new Color(56, 189, 248);
    public static final Color VERDE = new Color(52, 211, 153);
    public static final Color AMARILLO = new Color(251, 191, 36);
    public static final Color ROJO = new Color(248, 113, 113);
    public static final Color TEXTO = new Color(226, 232, 240);
    public static final Color TEXTO_SECUNDARIO = new Color(148, 163, 184);
    public static final Color BORDE = new Color(51, 65, 85);

    private EstiloNave() {}

    public static JPanel panel() {
        JPanel p = new JPanel();
        p.setBackground(PANEL);
        p.setBorder(new CompoundBorder(
                new LineBorder(BORDE, 1, true),
                new EmptyBorder(12, 12, 12, 12)
        ));
        return p;
    }

    public static JLabel titulo(String texto) {
        JLabel l = new JLabel(texto);
        l.setForeground(TEXTO);
        l.setFont(new Font("Segoe UI", Font.BOLD, 15));
        return l;
    }

    public static JLabel etiqueta(String texto) {
        JLabel l = new JLabel(texto);
        l.setForeground(TEXTO_SECUNDARIO);
        l.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        return l;
    }

    public static JButton boton(String texto, Color color) {
        JButton b = new JButton(texto);
        b.setFont(new Font("Segoe UI", Font.BOLD, 13));
        b.setForeground(Color.WHITE);
        b.setBackground(color);
        b.setFocusPainted(false);
        b.setBorder(new EmptyBorder(10, 16, 10, 16));
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.setOpaque(true);
        return b;
    }

    public static void estilizarArea(JTextArea area) {
        area.setBackground(FONDO);
        area.setForeground(TEXTO);
        area.setCaretColor(AZUL);
        area.setFont(new Font("Consolas", Font.PLAIN, 13));
        area.setBorder(new EmptyBorder(10, 10, 10, 10));
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
    }

    public static JScrollPane scroll(Component componente) {
        JScrollPane sp = new JScrollPane(componente);
        sp.setBorder(BorderFactory.createEmptyBorder());
        sp.getViewport().setBackground(FONDO);
        sp.getVerticalScrollBar().setUnitIncrement(16);
        return sp;
    }

    public static void estilizarBarra(JProgressBar barra, Color color) {
        barra.setStringPainted(true);
        barra.setForeground(color);
        barra.setBackground(PANEL_SECUNDARIO);
        barra.setBorder(BorderFactory.createEmptyBorder());
        barra.setFont(new Font("Segoe UI", Font.BOLD, 12));
        barra.setPreferredSize(new Dimension(150, 18));
    }
}
