package motor;

public interface State {

    public iniciarSalto(){
        System.out.println("no se pudo iniciar el salto");
    }
    public cancelarSalto(){
        System.out.println("no se pudo cancelar el salto");
    }
    public terminarWarp(){
        System.out.println("no se pudo termianr el warp");
    }
    public completarEnfriamiento(){
        System.out.println("no se pudo terminar el enfriamiento");
    }
    public completarPreparacion(){
        System.out.println("no se pudo terminar la preparacion");
    }
    public actulizar(){
        System.out.println("no se pudo actualizar");
    }
}
