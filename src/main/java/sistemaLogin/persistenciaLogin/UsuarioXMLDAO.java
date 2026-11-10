
package sistemaLogin.persistenciaLogin;

import java.beans.XMLEncoder;
import java.beans.XMLDecoder;
import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import sistemaLogin.modeloLogin.Usuario;

public class UsuarioXMLDAO implements UsuarioDAO {

    private static final String ARCHIVO = "datos/usuarios.xml";

    @Override
    public void guardarTodos(ArrayList<Usuario> usuarios) {

        try {
            Path ruta = Paths.get(ARCHIVO);

            // Crear la carpeta si no existe
            Files.createDirectories(ruta.getParent());

            try (FileOutputStream fos = new FileOutputStream(ruta.toFile());
                 XMLEncoder encoder = new XMLEncoder(fos)) {

                encoder.writeObject(usuarios);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public ArrayList<Usuario> obtenerTodos() {

        File archivo = new File(ARCHIVO);

        if (!archivo.exists()) {
            return new ArrayList<Usuario>();
        }

        try (FileInputStream fis = new FileInputStream(archivo);
             XMLDecoder decoder = new XMLDecoder(fis)) {

            return (ArrayList<Usuario>) decoder.readObject();

        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<Usuario>();
        }
    }
}
