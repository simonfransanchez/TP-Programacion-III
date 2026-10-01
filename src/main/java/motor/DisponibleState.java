package motor;

public class DisponibleState implements State{
    private motor m;

    public DisponibleState(motor mm){       //esto linkea el motor con esta clase
        this.m=mm;
    }
    public iniciarSalto(){
        System.out.println("se esta preaparando el salto");
        m.setEstado(new PreparandoSaltoState);        //aca cambio el estado a preparando
    }

}
