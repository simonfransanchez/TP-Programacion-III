package motor;

public class EnfriandoState implements State{
    private Motor m;

    public EnfriandoState(Motor mm){       //esto linkea el motor con esta clase
        this.m=mm;
    }

    public void completarEnfriamiento(){
        System.out.println("Listo el enfriamiento");
        m.setEstado(new DisponibleState(m));        //aca cambio el estado a preparando
    }

    public void iniciarSalto(){
        System.out.println("no se pudo iniciar el salto");
    }
    public void cancelarSalto(){
        System.out.println("no se pudo cancelar el salto");
    }
    public void terminarWarp(){System.out.println("no se pudo termianr el warp");}
    public void completarPreparacion(){
        System.out.println("no se pudo terminar la preparacion");
    }

}
