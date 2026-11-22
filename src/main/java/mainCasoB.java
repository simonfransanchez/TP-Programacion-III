import naves.*;
import misiones.*;
import asistente.*;

public class mainCasoB {
    public static void main(String[] args) {

        System.out.println("\n--- ESCENARIO B: RECURSOS INSUFICIENTES ---");

        NaveBase naveSinRecursos = NaveFactory.getNave("Exploradora");
        Asistente asistenteB = new Asistente(naveSinRecursos);

        // Dejar la nave sin combustible, si el metodo admite cero
        asistenteB.setCombustible(0);

        System.out.println("Estado antes de la mision:");
        System.out.println(naveSinRecursos);

        Mision misionB = FactoryMision.crearMision(1, asistenteB);

        try {
            misionB.realizarMision();
        } catch (RuntimeException e) {
            System.out.println("Mision rechazada: " + e.getMessage());
        }

        System.out.println("Estado despues del intento:");
        System.out.println(naveSinRecursos);

        System.out.println("Bitacora:");
        asistenteB.mostrarBitacora();


    }


}
