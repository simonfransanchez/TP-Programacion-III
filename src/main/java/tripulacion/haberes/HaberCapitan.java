package tripulacion.haberes;

public class HaberCapitan implements Haber {
    private static final double remuneracion = 1000;
    private static final double porcentajexantiguedad = 0.20;

    private final int antiguedad;

    public HaberCapitan(int antiguedad) {
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