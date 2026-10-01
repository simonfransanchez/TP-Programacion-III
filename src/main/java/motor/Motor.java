package motor;

public class Motor {
    private State estado;

    public void setEstado(State estado) {
        this.estado = estado;
    }

    public State getEstado() {
        return estado;
    }
}
