package motor;

public class Motor {
    private State estado;

    public Motor() {
        this.estado = new DisponibleState(this);
    }

    public void setEstado(State estado) {
        this.estado = estado;
    }

    public State getEstado() {
        return estado;
    }
    public void iniciarSalto(){
        estado.iniciarSalto();
    }
    public void cancelarSalto(){
        estado.cancelarSalto();
    }
    public void terminarWarp(){
        estado.terminarWarp();
    }
    public void completarEnfriamiento(){
        estado.completarEnfriamiento();
    }
    public void completarPreparacion(){
        estado.completarPreparacion();
    }
}
