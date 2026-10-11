
import java.util.List;
import naves.*;
import motor.*;
import misiones.*;
import asistente.*;
import tripulacion.*;
import tripulacion.haberes.*;

public class MainPruebas {

    public static void main(String[] args) {

        System.out.println("======================================");
        System.out.println("     TEST GENERAL DEL SISTEMA");
        System.out.println("======================================");

        // =====================================
        // 1. CREACIÓN DE NAVES (FACTORY)
        // =====================================

        System.out.println("\n--- 1. CREACION DE NAVES ---");

        // TODO: adaptar al nombre de tu Factory y sus métodos

        NaveBase exploradora = NaveFactory.getNave("Exploradora");
        NaveBase carguero = NaveFactory.getNave("Carguero");
        NaveBase combate = NaveFactory.getNave("Combate");

        // TODO: mostrar los datos de las naves creadas
        System.out.println(exploradora);
        System.out.println(carguero);
        System.out.println(combate);


        // =====================================
        // 2. PRUEBA DEL MOTOR WARP (STATE)
        // =====================================

        System.out.println("\n--- 2. MOTOR WARP ---");

        exploradora.iniciarSalto();

        exploradora.iniciarSalto(); // Debería rechazarlo
        // motor.completarPreparacion();
        exploradora.completarPreparacion();
        exploradora.terminarWarp();

        exploradora.iniciarSalto(); // Debería rechazarlo

        exploradora.completarEnfriamiento();

        // Estado: Disponible nuevamente
        System.out.println(exploradora.getMotor());


        // =====================================
        // 3. RECURSOS Y MANTENIMIENTO
        // =====================================

        System.out.println("\n--- 3. RECURSOS Y MANTENIMIENTO ---");

        // TODO: usar los métodos reales de Nave
        // Consultar energía y combustible iniciales
        // Intentar consumir recursos
        // Verificar que no queden valores negativos
        // Aplicar desgaste
        // Verificar si requiere mantenimiento

        // Ejemplo conceptual:
        // exploradora.consumirEnergia(cantidad);
        // exploradora.consumirCombustible(cantidad);
        // exploradora.aplicarDesgaste(cantidad);
        // exploradora.realizarMantenimiento();


        // =====================================
        // 4. MISIONES (TEMPLATE METHOD)
        // =====================================

        System.out.println("\n--- 4. MISIONES ---");

        // TODO: crear las misiones M-01, M-02 y M-03
        // Mision mision01 = new Mision01(...);
        // Mision mision02 = new Mision02(...);
        // Mision mision03 = new Mision03(...);

        // Ejecutar el ciclo completo definido por Template Method:
        // preparar -> ejecutar -> evaluar -> cerrar

        // mision01.realizarMision();
        // mision02.realizarMision();
        // mision03.realizarMision();


        // =====================================
        // 5. BITACORA
        // =====================================

        System.out.println("\n--- 5. BITACORA ---");

        Asistente asistente = new Asistente(carguero);
        asistente.cargarCombustible(4);
        asistente.cargarEnergia(4);
        asistente.mostrarBitacora();


        // =====================================
        // 6. LIQUIDACION DE SUELDOS (DECORATOR)
        // =====================================

        System.out.println("\n--- 6. LIQUIDACION DE SUELDOS ---");
        Tripulante t1 = new Tripulante("Juan", Cargo.CAPITAN, Origen.TERRICOLA,3);
        Tripulante t2 = new Tripulante("Ana", Cargo.CONSEJERO, Origen.VULCANO,5);
        Tripulante t3 = new Tripulante("Pedro", Cargo.TENIENTE, Origen.MARCIANO,0);
        Tripulante t4 = new Tripulante("Lucia", Cargo.ALFEREZ, Origen.TERRICOLA,2);
        Tripulante t5 = new Tripulante("Marcos", Cargo.TENIENTE, Origen.VULCANO,1);

        System.out.println("aca irian los sueldos");

        carguero.agregarTripulante(t1);
        carguero.agregarTripulante(t2);
        System.out.println(carguero.getTripulacion());

        System.out.println("\n======================================");
        System.out.println("       FIN DE LAS PRUEBAS");
        System.out.println("======================================");
    }
}

