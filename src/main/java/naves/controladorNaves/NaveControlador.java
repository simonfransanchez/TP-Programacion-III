package naves.controladorNaves;

import naves.vistaNaves.*;
import naves.NaveBase;
import naves.NaveFactory;

public class NaveControlador {

    private CrearNaveVista vista;
    private NaveBase nave;

    public NaveControlador(CrearNaveVista vista) {

        this.vista = vista;

        vista.getBtnCrear().addActionListener(e -> crearNave());
    }

    private void crearNave() {

        String tipo = vista.getTipoNave();

        nave = NaveFactory.getNave(tipo);

        if (nave == null) {
            vista.mostrarMensaje("No se pudo crear la nave.");
            return;
        }

        vista.mostrarMensaje(
                "¡Nave creada correctamente!\nTipo: " +
                        tipo + "\n" + nave.toString()
        );
    }

    public NaveBase getNave() {
        return nave;
    }
}
