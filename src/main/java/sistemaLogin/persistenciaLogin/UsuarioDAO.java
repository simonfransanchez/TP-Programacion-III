package sistemaLogin.persistenciaLogin;

public interface UsuarioDAO {

    void guardar(UsuarioDTO usuario);

    UsuarioDTO buscarPorNombre(String nombreUsuario);

    boolean existeUsuario(String nombreUsuario);
}
