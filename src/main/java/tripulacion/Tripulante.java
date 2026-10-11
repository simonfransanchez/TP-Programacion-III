package tripulacion;

public class Tripulante {
    private String identidad;
    private Cargo cargo;
    private Origen origen;
    private int antiguedad;

    public Tripulante(String identidad){
        this.identidad=identidad;
        this.cargo = null;
        this.origen = null;
        this.antiguedad = 0;
    }
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

    public String toString(){
        return this.cargo+": "+this.identidad;
    }

}
