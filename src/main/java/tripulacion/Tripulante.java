package tripulacion;

import tripulacion.haberes.Haber;
import tripulacion.haberes.HaberFactory;

public class Tripulante {
    private String identidad;
    private Cargo cargo;
    private Origen origen;
    private int antiguedad;
    private int consejos = 0;

    public Tripulante(String identidad, Cargo cargo, Origen origen, int antiguedad) {
        this.identidad = identidad;
        this.cargo = cargo;
        this.origen = origen;
        this.antiguedad = antiguedad;
    }

    public String getIdentidad() {
        return identidad;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public Origen getOrigen() {
        return origen;
    }

    public int getAntiguedad() {
        return antiguedad;
    }


     public void registrarConsejos(int cantidad) {
        if (cargo != Cargo.CONSEJERO) {
            throw new IllegalStateException("Solo un consejero puede registrar consejos!!!!");
        }
        if (cantidad < 0) {
            throw new IllegalArgumentException("Cantidad de consejos  negativa: " + cantidad);
        }
        this.consejos += cantidad;
    }

    public Haber getHaber() {
        Haber haber = HaberFactory.crearHaberCargo(cargo, antiguedad);
        if (cargo == Cargo.CONSEJERO) {
            haber = HaberFactory.agregarConsejos(haber, consejos);
        }
        return HaberFactory.decorarOrigen(origen, haber);
    }

    public String toString(){
        return this.cargo+": "+this.identidad;
    }

}
