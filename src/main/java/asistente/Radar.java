package asistente;

import destinoytablero.Posicion;
import destinoytablero.Tablero;
import elementos.ElementoDelUniverso;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Radares de la nave. Solo leen el tablero: no lo modifican ni deciden por la nave.
 * Clase auxiliar del asistente (visibilidad de paquete).
 */
final class Radar {

    private static final int[][] DIRECCIONES = {
            {-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

    private Radar() { }

    /**
     * Radar de largo alcance: calcula el camino mas corto (8 vecinos, un casillero por paso)
     * evitando los obstaculos visibles en este momento.
     * Pre:  tablero, origen y destino != null; ambas posiciones dentro del tablero
     * Post: lista que empieza en origen y termina en destino; vacia si no existe camino
     */
    static List<Posicion> calcularRuta(Tablero tablero, Posicion origen, Posicion destino) {
        ElementoDelUniverso[][] mapa = tablero.verTablero();
        Map<Posicion, Posicion> previo = new HashMap<Posicion, Posicion>();
        Deque<Posicion> cola = new ArrayDeque<Posicion>();
        previo.put(origen, origen);
        cola.add(origen);

        while (!cola.isEmpty()) {
            Posicion actual = cola.poll();
            if (actual.equals(destino)) {
                return reconstruir(previo, origen, destino);
            }
            for (int[] d : DIRECCIONES) {
                Posicion vecina = new Posicion(actual.getX() + d[0], actual.getY() + d[1]);
                if (dentro(tablero, vecina) && !previo.containsKey(vecina)
                        && (vecina.equals(destino) || !esObstaculo(mapa, vecina))) {
                    previo.put(vecina, actual);
                    cola.add(vecina);
                }
            }
        }
        return new ArrayList<Posicion>();
    }

    /**
     * Radar de corto alcance: mira solo los 8 casilleros que rodean a la nave.
     * Post: true si y solo si p es un vecino inmediato de la nave y hay un obstaculo en el
     */
    static boolean hayObstaculoEn(Tablero tablero, Posicion p) {
        ElementoDelUniverso[][] alrededores = tablero.observarAlrededores();
        Posicion nave = tablero.getPosicionNave();
        int i = p.getX() - nave.getX() + 1;
        int j = p.getY() - nave.getY() + 1;
        if (i < 0 || i > 2 || j < 0 || j > 2) {
            return false;
        }
        return alrededores[i][j] != null && alrededores[i][j].esObstaculo();
    }

    static boolean dentro(Tablero tablero, Posicion p) {
        return p.getX() >= 0 && p.getX() < tablero.getX() && p.getY() >= 0 && p.getY() < tablero.getY();
    }

    private static boolean esObstaculo(ElementoDelUniverso[][] mapa, Posicion p) {
        ElementoDelUniverso e = mapa[p.getX()][p.getY()];
        return e != null && e.esObstaculo();
    }

    private static List<Posicion> reconstruir(Map<Posicion, Posicion> previo, Posicion origen, Posicion destino) {
        ArrayList<Posicion> camino = new ArrayList<Posicion>();
        Posicion p = destino;
        while (!p.equals(origen)) {
            camino.add(p);
            p = previo.get(p);
        }
        camino.add(origen);
        Collections.reverse(camino);
        return camino;
    }
}
