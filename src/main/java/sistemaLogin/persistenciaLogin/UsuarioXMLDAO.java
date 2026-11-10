package sistemaLogin.persistenciaLogin;

import java.beans.XMLEncoder;
import java.beans.XMLDecoder;
import java.io.*;
import java.util.ArrayList;
import sistemaLogin.modeloLogin.Usuario;

public class UsuarioXMLDAO {

    private String archivo = "usuarios.xml";

    public void guardar(ArrayList<Usuario> usuarios) {

        try {
            FileOutputStream fos = new FileOutputStream(archivo);
            XMLEncoder encoder = new XMLEncoder(fos);

            encoder.writeObject(usuarios);

            encoder.close();
            fos.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Usuario> obtenerTodos() {

        File f = new File(archivo);

        if (!f.exists()) {
            return new ArrayList<Usuario>();
        }

        try {
            FileInputStream fis = new FileInputStream(archivo);
            XMLDecoder decoder = new XMLDecoder(fis);

            ArrayList<Usuario> usuarios =
                    (ArrayList<Usuario>) decoder.readObject();

            decoder.close();
            fis.close();

            return usuarios;

        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<Usuario>();
        }
    }
}
