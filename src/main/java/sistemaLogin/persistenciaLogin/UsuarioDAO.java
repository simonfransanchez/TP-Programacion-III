package sistemaLogin.persistenciaLogin;

import java.util.ArrayList;

public interface UsuarioDAO {

    ArrayList<UsuarioDTO> obtenerTodos();

    void guardarTodos(ArrayList<UsuarioDTO> usuarios);
}