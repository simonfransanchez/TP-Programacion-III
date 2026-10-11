package motor;
public class MainPruebas {

    public static void main(String[] args) {

        Motor motor = new Motor();

        System.out.println("===== TEST 1: INICIAR SALTO =====");
        System.out.println(motor.getEstado());
        motor.iniciarSalto();

        System.out.println("\n===== TEST 2: COMPLETAR PREPARACION =====");
        System.out.println(motor.getEstado());
        motor.completarPreparacion();

        System.out.println("\n===== TEST 3: TERMINAR WARP =====");
        System.out.println(motor.getEstado());
        motor.terminarWarp();

        System.out.println("\n===== TEST 4: COMPLETAR ENFRIAMIENTO =====");
        System.out.println(motor.getEstado());
        motor.completarEnfriamiento();

        System.out.println("\n===== TEST 5: INTENTAR CANCELAR EN DISPONIBLE =====");
        System.out.println(motor.getEstado());
        motor.cancelarSalto();

        System.out.println("\n===== TEST 6: INTENTAR TERMINAR WARP EN DISPONIBLE =====");
        System.out.println(motor.getEstado());
        motor.terminarWarp();

        System.out.println("\n===== FIN DE LAS PRUEBAS =====");
    }
}
