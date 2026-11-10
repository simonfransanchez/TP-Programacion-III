
package controlador;

import universoParalelo.SimuladorRecondito;
import vista.paneles.PanelVisorRecondito;

public class ControladorVisorRecondito {
    private final SimuladorRecondito simulador;
    private final PanelVisorRecondito vista;

    public ControladorVisorRecondito(SimuladorRecondito simulador, PanelVisorRecondito vista) {
        this.simulador = simulador;
        this.vista = vista;
        vista.getBotonGeneradora().addActionListener(e -> simulador.crearGeneradora());
        vista.getBotonNave().addActionListener(e -> simulador.crearNaveConsumidora());
    }
}
