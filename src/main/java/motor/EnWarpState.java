package motor;

public class EnWarpState implements State{
    private Motor m;

    public EnWarpState(Motor mm){       //esto linkea el motor con esta clase
        this.m=mm;
    }


    public void terminarWarp(){
        System.out.println("Se termino el warp");
        m.setEstado(new EnfriandoState(m));        //aca cambio el estado a preparando
    }

    public void iniciarSalto(){
        System.out.println("no se pudo iniciar el salto");
    }
    public void cancelarSalto(){
        System.out.println("no se pudo cancelar el salto");
    }
    public void completarEnfriamiento(){
        System.out.println("no se pudo terminar el enfriamiento");
    }
    public void completarPreparacion(){
        System.out.println("no se pudo terminar la preparacion");
    }

    @Override
    public boolean estaDisponible() {
        return false;
    }
    public String toString(){
        return "En Warp";
    }
}
