package controlador;

import naves.NaveBase;
import naves.NaveFactory;
import vista.*;

public class NaveControlador {

    private CrearNaveVista vista;
    private NaveBase nave;

    public NaveControlador(CrearNaveVista vista) {

        this.vista = vista;

        vista.getBtnCrear().addActionListener(e -> crearNave());
    }

    private void crearNave() {

        String tipo = vista.getTipoNave();

        if (tipo == null) {
            vista.mostrarMensaje("Primero seleccioná un tipo de nave.");
            return;
        }

        nave = NaveFactory.getNave(tipo);

        if (nave == null) {
            vista.mostrarMensaje("No se pudo crear la nave.");
            return;
        }

        // Cerrar la ventana de selección
        vista.dispose();

        // Abrir la ventana de creación de tripulación
        CrearTripulanteVista vistaTripulantes = new CrearTripulanteVista();

        TripulacionControlador controladorTripulacion = new TripulacionControlador(vistaTripulantes, nave);

        vistaTripulantes.setVisible(true);
    }

    public NaveBase getNave() {
        return nave;
    }
}