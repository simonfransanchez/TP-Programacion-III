import java.util.List;
import naves.*;
import motor.*;
import misiones.*;
import asistente.*;
import tripulacion.*;
import tripulacion.haberes.*;

public class mainCasoD {
    public static void main(String[] args) {

        System.out.println("\n--- ESCENARIO D: CONTRATO INVALIDO ---");

        NaveBase naveD = NaveFactory.getNave("Carguero");
        Asistente asistenteD = new Asistente(naveD);

        System.out.println("Estado antes de la carga:");
        System.out.println(naveD);

        // Intentar superar la capacidad de la nave
        asistenteD.cargarCombustible(999999);

        System.out.println("Estado despues de la carga:");
        System.out.println(naveD);

        System.out.println("Bitacora:");
        asistenteD.mostrarBitacora();

    }


}
