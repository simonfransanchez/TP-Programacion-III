
import sistemaLogin.vistaLogin.LoginVista;
import javax.swing.SwingUtilities;

public class mainPruebaVistaLogin {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            LoginVista login = new LoginVista();
            login.setVisible(true);
        });
    }
}
