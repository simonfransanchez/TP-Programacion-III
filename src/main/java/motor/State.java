package motor;

public interface State {

    /** Post: devuelve true si y solo si el estado es Disponible */
    boolean estaDisponible();

    /** Pre:  estado actual == Disponible
     *  Post: motor pasa a PreparandoSalto */
    void iniciarSalto();

    /** Pre:  estado actual == PreparandoSalto
     *  Post: motor pasa a Disponible */
    void cancelarSalto();

    /** Pre:  estado actual == PreparandoSalto
     *  Post: motor pasa a EnWarp */
    void completarPreparacion();

    /** Pre:  estado actual == EnWarp
     *  Post: motor pasa a Enfriando */
    void terminarWarp();

    /** Pre:  estado actual == Enfriando
     *  Post: motor pasa a Disponible */
    void completarEnfriamiento();
}
