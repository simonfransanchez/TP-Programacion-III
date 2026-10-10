package motor;

public class DisponibleState implements State{
    private Motor m;

    public DisponibleState(Motor mm){       //esto linkea el motor con esta clase
        this.m=mm;
    }

    public void iniciarSalto(){
        System.out.println("se esta preaparando el salto");
        m.setEstado(new PreparandoSaltoState(m));        //aca cambio el estado a preparando
    }

    public void cancelarSalto(){
        System.out.println("no se pudo cancelar el salto");
    }
    public void terminarWarp(){System.out.println("no se pudo termianr el warp");}
    public void completarEnfriamiento(){
        System.out.println("no se pudo terminar el enfriamiento");
    }
    public void completarPreparacion(){
        System.out.println("no se pudo terminar la preparacion");
    }

    @Override
    public boolean estaDisponible() {
        return true;
    }
}
