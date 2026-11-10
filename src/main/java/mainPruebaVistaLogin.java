

import sistemaLogin.vistaLogin.LoginVista;
import sistemaLogin.persistenciaLogin.UsuarioDAO;
import sistemaLogin.persistenciaLogin.UsuarioXMLDAO;
import sistemaLogin.controladorLogin.LoginControlador;

public class mainPruebaVistaLogin {

    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(() -> {

            UsuarioDAO dao = new UsuarioXMLDAO();

            LoginVista login = new LoginVista();

            new LoginControlador(login, dao);

            login.setVisible(true);
        });
    }
}
