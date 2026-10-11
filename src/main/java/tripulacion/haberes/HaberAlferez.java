package tripulacion.haberes;

public class HaberAlferez implements Haber {
    private static final double remuneracion = 200;
    private static final double porcentajexantiguedad = 0.005;

    private final int antiguedad;

    public HaberAlferez(int antiguedad) {
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