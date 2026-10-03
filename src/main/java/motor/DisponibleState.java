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
}
