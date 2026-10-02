package misiones;

public abstract class Mision {
    private Nave nave;
    private Informe informe;

    public Mision(Nave nave) {
        this.nave = nave;
    }

    public Informe realizarMision(){
        preparar();
        ejecutar();
        evaluarResultado();
        cerrar();
    }

    private abstract void preparar();

    private abstract void evaluarResultado();

    private abstract void ejecutar();

    private abstract void cerrar();
}
