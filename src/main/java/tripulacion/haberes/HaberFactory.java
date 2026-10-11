package tripulacion.haberes;

import tripulacion.Cargo;
import tripulacion.Origen;

public class HaberFactory {

    public static Haber crearHaberCargo(Cargo cargo, int antiguedad) {
        if (cargo == null) {
            throw new IllegalArgumentException("El cargo no puede ser nulo");
        }
        switch (cargo) {
            case CAPITAN:
                return new HaberCapitan(antiguedad);
            case CONSEJERO:
                return new HaberConsejero(antiguedad);
            case TENIENTE:
                return new HaberTeniente(antiguedad);
            case ALFEREZ:
                return new HaberAlferez(antiguedad);
            default:
                throw new IllegalArgumentException("Cargo no soportado: " + cargo);
        }
    }

    public static Haber decorarOrigen(Origen origen, Haber haber) {
        if (origen == null) {
            throw new IllegalArgumentException("El origen no puede ser nulo");
        }
        switch (origen) {
            case TERRICOLA:
                return new Terricola(haber);
            case VULCANO:
                return new Vulcano(haber);
            case MARCIANO:
                return new Marciano(haber);
            default:
                throw new IllegalArgumentException("Origen no soportado: " + origen);
        }
    }

    public static Haber agregarConsejos(Haber haber, int consejos) {
        return new AdicionalConsejos(haber, consejos);
    }
}