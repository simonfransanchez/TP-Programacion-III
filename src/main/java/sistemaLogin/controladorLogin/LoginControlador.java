
package sistemaLogin.controladorLogin;

import sistemaLogin.modeloLogin.Usuario;
import sistemaLogin.persistenciaLogin.UsuarioDAO;
import sistemaLogin.vistaLogin.LoginVista;
import sistemaLogin.vistaLogin.RegistroVista;
import naves.vistaNaves.*;
import naves.controladorNaves.*;

import java.util.ArrayList;

public class LoginControlador {

    private LoginVista vista;
    private UsuarioDAO dao;

    public LoginControlador(LoginVista vista, UsuarioDAO dao) {

        this.vista = vista;
        this.dao = dao;

        vista.getBtnIngresar().addActionListener(e -> ingresar());
        vista.getBtnRegistrarse().addActionListener(e -> abrirRegistro());
    }

    private void ingresar() {

        String nombre = vista.getUsuario().trim();
        String password = new String(vista.getPassword());

        if (nombre.isEmpty() || password.isEmpty()) {

            vista.mostrarMensaje("Complete todos los campos.");
            return;
        }

        ArrayList<Usuario> usuarios = dao.obtenerTodos();

        for (Usuario usuario : usuarios) {

            if (usuario.getNombre().equalsIgnoreCase(nombre)
                    && usuario.getPassword().equals(password)) {

                vista.mostrarMensaje("¡Bienvenido, " + nombre + "!");

                vista.limpiarCampos();
                vista.dispose();

                CrearNaveVista crearNave = new CrearNaveVista();
                new NaveControlador(crearNave);
                crearNave.setVisible(true);

                return;
            }
        }

        vista.mostrarMensaje("Usuario o contraseña incorrectos.");
    }

    private void abrirRegistro() {

        vista.setVisible(false);

        RegistroVista registro = new RegistroVista();

        new RegistroControlador(registro, vista, dao);

        registro.setVisible(true);
    }
}

