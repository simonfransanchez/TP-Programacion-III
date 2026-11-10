package controlador;

import asistente.Asistente;
import misiones.FactoryMision;
import misiones.Informe;
import misiones.Mision;
import vista.VistaControlNave;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorControlNave implements ActionListener {
    private final Asistente asistente;
    private final VistaControlNave vista;

    public ControladorControlNave(Asistente asistente, VistaControlNave vista) {
        this.asistente = asistente;
        this.vista = vista;
        vista.setControlador(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        final Mision mision = FactoryMision.crearMision(vista.getNumeroMision(), asistente);
        vista.habilitarInicio(false);
        new SwingWorker<Informe, Void>() {
            @Override protected Informe doInBackground() {
                return mision.realizarMision();            // corre fuera del hilo de eventos
            }
            @Override protected void done() {
                try { vista.mostrarResultado(get()); }
                catch (Exception ex) { vista.mostrarError("La misión terminó con un error: " + ex.getMessage()); }
                vista.habilitarInicio(true);
            }
        }.execute();
    }
}