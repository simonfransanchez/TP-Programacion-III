package tripulacion.haberes;

public class HaberConsejero implements Haber {
    private static final double remuneracion = 600;
    private static final double porcentajexantiguedad = 0.05;

    private final int antiguedad;

    public HaberConsejero(int antiguedad) {
        if (antiguedad < 0) {
            throw new IllegalArgumentException("La antigüedad no puede ser negativa: " + antiguedad);
        }
        this.antiguedad = antiguedad;
    }

    @Override
    public double calcularHaber() {
        return remuneracion + (remuneracion * porcentajexantiguedad * antiguedad);
    }
}