package motor;

public class EnfriandoState implements State{
    private Motor m;

    public EnfriandoState(Motor mm){       //esto linkea el motor con esta clase
        this.m=mm;
    }

    public void completarEnfriamiento(){
        System.out.println("no se pudo terminar el enfriamiento");
        m.setEstado(new DisponibleState(m));        //aca cambio el estado a preparando
    }

}
