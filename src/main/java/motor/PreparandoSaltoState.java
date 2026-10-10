package motor;

public class PreparandoSaltoState implements State{
    private Motor m;

    public DisponibleState(Motor mm){       //esto linkea el motor con esta clase
        this.m=mm;
    }

    public void cancelarSalto(){
        System.out.println("Se cancelo el salto ");
        m.setEstado(new DisponibleState(m));        //aca cambio el estado a preparando
    }

    public void completarPreparacion(){

        System.out.println("Motor listo para salto");
        m.setEstado(new EnWarpState(m));        //aca cambio el estado a preparando
    }

    public void iniciarSalto(){

        System.out.println("no se pudo iniciar el salto");
    }
    public void terminarWarp(){
        System.out.println("no se pudo termianr el warp");
    }
    public void completarEnfriamiento(){
        System.out.println("no se pudo terminar el enfriamiento");
    }
}
