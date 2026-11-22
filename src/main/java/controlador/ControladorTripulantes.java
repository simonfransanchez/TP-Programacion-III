package controlador;
import naves.NaveBase;
import tripulacion.Cargo;
import tripulacion.Tripulante;
import vista.VistaTripulantes;

public class ControladorTripulantes {

    private VistaTripulantes vista;
    private NaveBase nave;

    public ControladorTripulantes(VistaTripulantes vista, NaveBase nave) {
        this.vista = vista;
        this.nave = nave;

        vista.getBtnCerrar().addActionListener(e -> vista.dispose());
    }

 /*Le pide la tripulación al modelo (la nave) y se la pasa a la vista.*/
    public void mostrarTripulantes() {
        vista.limpiar();

        double total = 0;

        if (nave.getTripulacion().isEmpty()) {
            vista.agregarLinea("La nave no tiene tripulantes.");
        }

        for (Tripulante t : nave.getTripulacion()) {
            String linea = t.getIdentidad()
                    + " | Cargo: " + t.getCargo()
                    + " | Origen: " + t.getOrigen()
                    + " | Antigüedad: " + t.getAntiguedad() + " años";

            if (t.getCargo() == Cargo.CONSEJERO) {
                linea = linea + " | Consejos: " + t.getConsejos();
            }

            linea = linea + " | Salario: " + t.getSalario() + " PG";

            vista.agregarLinea(linea);
            total = total + t.getSalario();
        }

        vista.mostrarTotal(total);
        vista.setVisible(true);
    }
}