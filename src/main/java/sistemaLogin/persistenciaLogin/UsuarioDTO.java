package sistemaLogin.persistenciaLogin;

public class UsuarioDTO {

    private String nombre;
    private String password;

    public UsuarioDTO(String nombre, String password) {
        this.nombre = nombre;
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPassword() {
        return password;
    }
}