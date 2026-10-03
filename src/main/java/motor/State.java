package motor;

public interface State {

    default void iniciarSalto(){
        System.out.println("no se pudo iniciar el salto");
    }
    default void cancelarSalto(){
        System.out.println("no se pudo cancelar el salto");
    }
    default void terminarWarp(){
        System.out.println("no se pudo termianr el warp");
    }
    default void completarEnfriamiento(){
        System.out.println("no se pudo terminar el enfriamiento");
    }
    default void completarPreparacion(){
        System.out.println("no se pudo terminar la preparacion");
    }
}
