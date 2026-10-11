package motor;

/**
 * Inv: estado != null y es uno de {Disponible, PreparandoSalto, EnWarp, Enfriando}
 */
public class Motor {
    private State estado;

    public Motor() {
        this.estado = new DisponibleState(this);
    }

    /**
     * Pre:  estado != null
     * Post: getEstado() == estado
     */
    public void setEstado(State estado) {
        this.estado = estado;
    }

    public State getEstado() {
        return estado;
    }

    /**
     * Post: delega en el estado actual; el motor pasa a un nuevo estado
     *       solo si la transicion es valida
     */
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
    public String toString(){
        return "MotorEstado:"+this.getEstado();
    }
}
