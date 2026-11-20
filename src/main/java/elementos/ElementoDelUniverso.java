package elementos;

import asistente.Asistente;

public interface ElementoDelUniverso {

    /** Qué le pasa a la nave propia (a través de su asistente) al coincidir con este elemento.
     *  Pre: asistente != null */
    void interactuar(Asistente asistente);

    /** Nombre para bitácora y vistas. La vista no necesita conocer clases concretas. */
    String descripcion();

    /** Lo que el radar de corto alcance considera un obstáculo (decide el salto warp). */
    default boolean esObstaculo() { return false; }
}