package motor;

public interface State {
    boolean estaDisponible();
    void iniciarSalto();
    void cancelarSalto();
    void terminarWarp();
    void completarEnfriamiento();
    void completarPreparacion();
}
