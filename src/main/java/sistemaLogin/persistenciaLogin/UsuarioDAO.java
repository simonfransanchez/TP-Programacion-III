package sistemaLogin.persistenciaLogin;

import sistemaLogin.modeloLogin.Usuario;
import java.util.ArrayList;

public interface UsuarioDAO {

    ArrayList<Usuario> obtenerTodos();

    void guardarTodos(ArrayList<Usuario> usuarios);
}
