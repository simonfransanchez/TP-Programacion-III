package controlador;

import naves.NaveBase;
import vista.CrearTripulanteVista;
import tripulacion.Tripulante;
import universoParalelo.SimuladorRecondito;
import tripulacion.Cargo;
import tripulacion.Origen;
import asistente.Asistente;
import destinoytablero.Tablero;
import elementos.ElementoDelUniverso;
import vista.VistaControlNave;
import vista.paneles.PanelVisor;
import vista.VistaTripulantes;

import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class TripulacionControlador {

    private CrearTripulanteVista vista;
    private NaveBase nave;

    public TripulacionControlador(
            CrearTripulanteVista vista,
            NaveBase nave
    ) {

        this.vista = vista;
        this.nave = nave;

        vista.getBtnAgregar().addActionListener(e -> agregarTripulante());
        vista.getBtnEliminar().addActionListener(e -> eliminarTripulante());
        vista.getBtnFinalizar().addActionListener(e -> finalizar());
    }

    private void agregarTripulante() {

        String identidad = vista.getIdentidad();
        String antiguedadTexto = vista.getAntiguedad();

        if (identidad.isEmpty() || antiguedadTexto.isEmpty()) {
            vista.mostrarError("Completá todos los campos obligatorios.");
            return;
        }

        int antiguedad;

        try {
            antiguedad = Integer.parseInt(antiguedadTexto);

            if (antiguedad < 0) {
                vista.mostrarError("La antigüedad no puede ser negativa.");
                return;
            }

        } catch (NumberFormatException e) {
            vista.mostrarError("La antigüedad debe ser un número entero.");
            return;
        }

        Cargo cargo = vista.getCargo();
        Origen origen = vista.getOrigen();

        Tripulante nuevoTripulante = new Tripulante(
                identidad,
                cargo,
                origen,
                antiguedad
        );

        // Si es consejero, registrar sus consejos
        if (cargo == Cargo.CONSEJERO) {

            int consejos;

            try {
                consejos = Integer.parseInt(vista.getConsejos());

                if (consejos < 0) {
                    vista.mostrarError("Los consejos no pueden ser negativos.");
                    return;
                }

            } catch (NumberFormatException e) {
                vista.mostrarError("La cantidad de consejos debe ser un número entero.");
                return;
            }

            nuevoTripulante.registrarConsejos(consejos);
        }

        // Agregar al modelo de la nave
        nave.agregarTripulante(nuevoTripulante);

        // Actualizar la vista
        vista.agregarATabla(nuevoTripulante);
        vista.limpiarCampos();
    }

    private void eliminarTripulante() {

        Tripulante seleccionado = vista.getTripulanteSeleccionado();

        if (seleccionado == null) {
            vista.mostrarError("Seleccioná un tripulante para eliminar.");
            return;
        }

        nave.quitarTripulante(seleccionado);
        vista.eliminarDeLista(seleccionado);
    }

    
private void finalizar() {

    ArrayList<ElementoDelUniverso> elementos = new ArrayList<>();

    Tablero tablero;
    try {
        tablero = new Tablero(10, 10, elementos);
    } catch (Exception e) {
        vista.mostrarError(
            "No se pudo crear el tablero: " + e.getMessage()
        );
        return;
    }

    Asistente asistente = new Asistente(nave);

    VistaControlNave vistaControl =
            new VistaControlNave(asistente, tablero);

    new ControladorControlNave(asistente, vistaControl);

            vistaControl.getBtnVerTripulacion().addActionListener(e -> {
            VistaTripulantes vistaTripulantes = new VistaTripulantes();
            ControladorTripulantes controladorTripulantes = new ControladorTripulantes(vistaTripulantes, nave);
            controladorTripulantes.mostrarTripulantes();
        });

    // CREAR VISOR RECÓNDITO
    SimuladorRecondito simulador = new SimuladorRecondito(5);

    PanelVisor panelVisor = new PanelVisor(simulador);

    new ControladorVisor(panelVisor, simulador);

    // UNIR AMBOS PANELES
    JTabbedPane pestanas = new JTabbedPane();

    pestanas.addTab("Control de nave", vistaControl);
    pestanas.addTab("Visor Recóndito", panelVisor);

    // VENTANA PRINCIPAL
    JFrame ventana = new JFrame("Sistema de control de nave");
    ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    ventana.setSize(1000, 700);
    ventana.setLocationRelativeTo(null);
    ventana.setContentPane(pestanas);
    ventana.setVisible(true);

    vista.dispose();
}


    public NaveBase getNave() {
        return nave;
    }
}