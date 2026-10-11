package motor;

public class DisponibleState implements State{
    private Motor m;

    public DisponibleState(Motor mm){       //esto linkea el motor con esta clase
        this.m=mm;
    }

    /**
     * Pre:  el motor esta en Disponible
     * Post: m.getEstado() es PreparandoSaltoState
     */
    public void iniciarSalto(){
        System.out.println("se esta preaparando el salto");
        m.setEstado(new PreparandoSaltoState(m));        //aca cambio el estado a preparando
    }

    /** Post: el estado no cambia (operacion invalida en Disponible) */
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

    /** Post: devuelve true */
    @Override
    public boolean estaDisponible() {
        return true;
    }
    public String toString(){
        return "Disponible";
    }
}
