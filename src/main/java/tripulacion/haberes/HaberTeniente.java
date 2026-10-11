package tripulacion.haberes;

public class HaberTeniente implements Haber {
    private static final double remuneracion = 400;
    private static final double porcentajexantiguedad = 0.03;

    private final int antiguedad;

    public HaberTeniente(int antiguedad) {
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