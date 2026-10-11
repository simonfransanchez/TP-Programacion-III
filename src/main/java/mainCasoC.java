import java.util.List;
import naves.*;
import motor.*;
import misiones.*;
import asistente.*;
import tripulacion.*;
import tripulacion.haberes.*;

public class mainCasoC {
    public static void main(String[] args) {

        System.out.println("\n--- ESCENARIO C: MOTOR WARP ---");

        NaveBase naveWarp = NaveFactory.getNave("Exploradora");
        System.out.println(naveWarp.getMotor());

    // Secuencia valida
        naveWarp.iniciarSalto();
        naveWarp.completarPreparacion();
        naveWarp.terminarWarp();
        naveWarp.completarEnfriamiento();

        System.out.println("\n\nEstado final de la secuencia valida:");
        System.out.println(naveWarp.getMotor());

    // Transicion invalida: intentar terminar warp estando disponible
        System.out.println("\nIntentando transicion invalida:");
        naveWarp.terminarWarp();

        System.out.println("Estado despues del intento:");
        System.out.println(naveWarp.getMotor());


    }


}
