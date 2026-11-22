package sistemaLogin.controladorLogin;

import sistemaLogin.persistenciaLogin.UsuarioDAO;
import sistemaLogin.persistenciaLogin.UsuarioDTO;
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

        if (nombre.isEmpty() || password.isEmpty()
                || repetirPassword.isEmpty()) {

            vista.mostrarMensaje("Todos los campos son obligatorios.");
            return;
        }

        if (!password.equals(repetirPassword)) {

            vista.mostrarMensaje("Las contraseñas no coinciden.");
            return;
        }

        ArrayList<UsuarioDTO> usuarios = dao.obtenerTodos();

        for (UsuarioDTO usuario : usuarios) {

            if (usuario.getNombre().equalsIgnoreCase(nombre)) {

                vista.mostrarMensaje("Ese nombre de usuario ya existe.");
                return;
            }
        }

        UsuarioDTO nuevoUsuario = new UsuarioDTO(nombre, password);

        usuarios.add(nuevoUsuario);
        dao.guardarTodos(usuarios);

        vista.limpiarCampos();
        vista.dispose();
        login.setVisible(true);
    }

    private void volver() {

        vista.dispose();
        login.setVisible(true);
    }
}