
import java.util.List;
import naves.*;
import motor.*;
import misiones.*;
import asistente.*;
import tripulacion.*;
import tripulacion.haberes.*;

public class mainCasoA {
    public static void main(String[] args) {
    System.out.println("\n--- ESCENARIO A: EJECUCION CORRECTA ---");

    // Crear una nave de cada tipo
    NaveBase exploradora = NaveFactory.getNave("Exploradora");
    NaveBase carguero = NaveFactory.getNave("Carguero");
    NaveBase combate = NaveFactory.getNave("Combate");

    // Crear tripulacion
    Tripulante capitan = new Tripulante(
            "Juan", Cargo.CAPITAN, Origen.TERRICOLA, 3
    );
    Tripulante consejera = new Tripulante(
            "Ana", Cargo.CONSEJERO, Origen.VULCANO, 5
    );
    Tripulante teniente = new Tripulante(
            "Pedro", Cargo.TENIENTE, Origen.MARCIANO, 0
    );
    Tripulante alferez = new Tripulante(
            "Lucia", Cargo.ALFEREZ, Origen.TERRICOLA, 2
    );
    Tripulante teniente2 = new Tripulante(
            "Marcos", Cargo.TENIENTE, Origen.VULCANO, 1
    );

// Asignar tripulacion
    exploradora.agregarTripulante(capitan);
    exploradora.agregarTripulante(consejera);

    carguero.agregarTripulante(teniente);
    carguero.agregarTripulante(alferez);

    combate.agregarTripulante(teniente2);

    // Mostrar estado inicial
    System.out.println("\nEstado inicial:");
    System.out.println(exploradora);
    System.out.println(carguero);
    System.out.println(combate);

    // Crear un asistente para cada nave
    Asistente asistente01 = new Asistente(exploradora);
    Asistente asistente02 = new Asistente(carguero);
    Asistente asistente03 = new Asistente(combate);

    // Crear misiones
    Mision mision01 = FactoryMision.crearMision(1, asistente01);
    Mision mision02 = FactoryMision.crearMision(2, asistente02);
    Mision mision03 = FactoryMision.crearMision(3, asistente03);

    // Ejecutar el ciclo de cada mision
    System.out.println("\nEjecutando M-01:");
    mision01.realizarMision();
    System.out.println(exploradora);
    asistente01.mostrarBitacora();

    System.out.println("\nEjecutando M-02:");
    mision02.realizarMision();
    System.out.println(carguero);
    asistente02.mostrarBitacora();

    System.out.println("\nEjecutando M-03:");
    mision03.realizarMision();
    System.out.println(combate);
    asistente03.mostrarBitacora();

    }
}
