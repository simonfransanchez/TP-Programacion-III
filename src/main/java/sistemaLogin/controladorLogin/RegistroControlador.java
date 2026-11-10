
package sistemaLogin.controladorLogin;

import sistemaLogin.modeloLogin.Usuario;
import sistemaLogin.persistenciaLogin.UsuarioDAO;
import sistemaLogin.vistaLogin.LoginVista;
import sistemaLogin.vistaLogin.RegistroVista;

import java.util.ArrayList;

public class RegistroControlador {

    private RegistroVista vista;
    private LoginVista login;
    private UsuarioDAO dao;

    public RegistroControlador(
            RegistroVista vista,
            LoginVista login,
            UsuarioDAO dao) {

        this.vista = vista;
        this.login = login;
        this.dao = dao;

        vista.getBtnRegistrarse().addActionListener(e -> registrar());
        vista.getBtnVolver().addActionListener(e -> volver());
    }

    private void registrar() {

        String nombre = vista.getUsuario().trim();
        String password = new String(vista.getPassword());
        String repetirPassword = new String(vista.getRepetirPassword());

        // Validar campos vacíos
        if (nombre.isEmpty() || password.isEmpty()
                || repetirPassword.isEmpty()) {

            vista.mostrarMensaje("Todos los campos son obligatorios.");
            return;
        }

        // Validar coincidencia de contraseñas
        if (!password.equals(repetirPassword)) {

            vista.mostrarMensaje("Las contraseñas no coinciden.");
            return;
        }

        // Obtener usuarios existentes
        ArrayList<Usuario> usuarios = dao.obtenerTodos();

        // Verificar si el usuario ya existe
        for (Usuario usuario : usuarios) {

            if (usuario.getNombre().equalsIgnoreCase(nombre)) {

                vista.mostrarMensaje("Ese nombre de usuario ya existe.");
                return;
            }
        }

        // Crear y guardar el nuevo usuario
        Usuario nuevoUsuario = new Usuario(nombre, password);

        usuarios.add(nuevoUsuario);
        dao.guardarTodos(usuarios);

        vista.mostrarMensaje("¡Usuario registrado correctamente!");

        vista.limpiarCampos();
        vista.dispose();
        login.setVisible(true);
    }

    private void volver() {

        vista.dispose();
        login.setVisible(true);
    }
}
