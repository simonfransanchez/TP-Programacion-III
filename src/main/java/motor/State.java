package motor;

public interface State {
    void iniciarSalto();
    void cancelarSalto();
    void terminarWarp();
    void completarEnfriamiento();
    void completarPreparacion();
}
