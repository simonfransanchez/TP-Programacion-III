package controlador;

import vista.paneles.PanelVisor;
import universoParalelo.SimuladorRecondito;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorVisor implements ActionListener {

    private final PanelVisor vista;
    private final SimuladorRecondito modelo;

    public ControladorVisor(PanelVisor vista, SimuladorRecondito modelo) {

        this.vista = vista;
        this.modelo = modelo;

        vista.agregarListenerGeneradora(this);
        vista.agregarListenerNave(this);
        vista.agregarListenerDetener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {

            case "Crear Generadora":
                modelo.crearGeneradora();
                break;

            case "Crear Nave Consumidora":
                modelo.crearNaveConsumidora();
                break;
            case "Detener simulación":
                modelo.detenerSimulacion();
                vista.deshabilitarBotones();
                break;
        }
    }
}