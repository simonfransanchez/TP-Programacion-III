
package vista.paneles;

import destinoytablero.*;
import elementos.ElementoDelUniverso;

import javax.swing.*;
import java.awt.*;

public class PanelTablero extends JPanel implements Observador {

    private final Tablero tablero;
    private volatile ElementoDelUniverso[][] celdas;
    private volatile Posicion nave;

    public PanelTablero(Tablero tablero) {
        this.tablero = tablero;
        this.celdas = tablero.verTablero();
        this.nave = tablero.getPosicionNave();

        setPreferredSize(new Dimension(420, 420));
        setBackground(EstiloNave.FONDO);

        tablero.agregarObservador(this);
    }

    @Override
    public void actualizar() {
        celdas = tablero.verTablero();
        nave = tablero.getPosicionNave();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        ElementoDelUniverso[][] c = celdas;
        Posicion n = nave;

        if (c == null || c.length == 0 || c[0].length == 0) return;

        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        int filas = c.length;
        int columnas = c[0].length;

        int lado = Math.min(getWidth() / columnas, getHeight() / filas);

        int anchoTablero = lado * columnas;
        int altoTablero = lado * filas;

        int inicioX = (getWidth() - anchoTablero) / 2;
        int inicioY = (getHeight() - altoTablero) / 2;

        // Fondo del tablero
        g2.setColor(EstiloNave.FONDO);
        g2.fillRect(0, 0, getWidth(), getHeight());

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {

                int x = inicioX + j * lado;
                int y = inicioY + i * lado;

                // Fondo de cada celda
                g2.setColor(new Color(22, 30, 45));
                g2.fillRoundRect(x + 2, y + 2, lado - 4, lado - 4,
                        8, 8);

                // Elementos
                if (c[i][j] != null) {
                    dibujarElemento(g2, c[i][j], x, y, lado);
                }

                // Bordes
                g2.setColor(new Color(51, 65, 85));
                g2.drawRoundRect(x + 2, y + 2, lado - 4, lado - 4,
                        8, 8);
            }
        }

        // Nave
        if (n != null) {
            int x = inicioX + n.getY() * lado;
            int y = inicioY + n.getX() * lado;

            g2.setColor(new Color(56, 189, 248, 45));
            g2.fillOval(x + 3, y + 3, lado - 6, lado - 6);

            g2.setColor(EstiloNave.AZUL);
            g2.setStroke(new BasicStroke(2.5f));

            int centroX = x + lado / 2;
            int centroY = y + lado / 2;
            int radio = Math.max(5, lado / 4);

            g2.drawOval(centroX - radio, centroY - radio,
                    radio * 2, radio * 2);

            // Representación de la nave
            int[] px = {
                    centroX,
                    centroX - radio / 2,
                    centroX + radio / 2
            };

            int[] py = {
                    centroY - radio / 2,
                    centroY + radio / 2,
                    centroY + radio / 2
            };

            g2.fillPolygon(px, py, 3);
        }

        g2.dispose();
    }

    private void dibujarElemento(Graphics2D g2,
                                 ElementoDelUniverso elemento,
                                 int x, int y, int lado) {

        String nombre = elemento.getClass().getSimpleName();
        String simbolo = nombre.substring(0, 1).toUpperCase();

        Color color = EstiloNave.AMARILLO;

        // Colores según tipo de elemento
        if (nombre.toLowerCase().contains("asteroide")) {
            color = new Color(148, 163, 184);
        } else if (nombre.toLowerCase().contains("planeta")) {
            color = new Color(167, 139, 250);
        } else if (nombre.toLowerCase().contains("estacion")) {
            color = EstiloNave.VERDE;
        } else if (nombre.toLowerCase().contains("estrella")) {
            color = EstiloNave.AMARILLO;
        }

        int centroX = x + lado / 2;
        int centroY = y + lado / 2;
        int radio = Math.max(5, lado / 3);

        g2.setColor(color);
        g2.fillOval(centroX - radio, centroY - radio,
                radio * 2, radio * 2);

        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Segoe UI", Font.BOLD,
                Math.max(10, lado / 3)));

        FontMetrics fm = g2.getFontMetrics();

        int tx = centroX - fm.stringWidth(simbolo) / 2;
        int ty = centroY + fm.getAscent() / 2 - 2;

        g2.drawString(simbolo, tx, ty);
    }
}
