package vista.paneles;

public class PanelTablero extends JPanel implements Observador {
    private final Tablero tablero;
    private volatile ElementoDelUniverso[][] celdas;
    private volatile Posicion nave;

    public PanelTablero(Tablero tablero) {
        this.tablero = tablero;
        this.celdas = tablero.verTablero();
        this.nave = tablero.getPosicionNave();
        setPreferredSize(new Dimension(420, 420));
        tablero.agregarObservador(this);
    }

    @Override
    public void actualizar() {                      // lo llama el hilo de la misión
        celdas = tablero.verTablero();              // la copia se toma acá, sin carrera con Destino
        nave = tablero.getPosicionNave();
        repaint();                                  // repaint() se puede llamar desde cualquier hilo
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ElementoDelUniverso[][] c = celdas;
        Posicion n = nave;
        int filas = c.length, columnas = c[0].length;
        int lado = Math.min(getWidth() / columnas, getHeight() / filas);
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                g.setColor(Color.LIGHT_GRAY);
                g.drawRect(j * lado, i * lado, lado, lado);
                if (c[i][j] != null) {
                    g.setColor(Color.BLACK);
                    // provisorio: cuando exista ElementoDelUniverso, pedirle un símbolo
                    g.drawString(c[i][j].getClass().getSimpleName().substring(0, 1),
                            j * lado + lado / 3, i * lado + 2 * lado / 3);
                }
            }
        }
        g.setColor(Color.BLUE);
        g.fillOval(n.getY() * lado + 2, n.getX() * lado + 2, lado - 4, lado - 4);   // x = fila, y = columna
    }
}