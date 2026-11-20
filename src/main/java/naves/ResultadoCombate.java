package naves;

public enum ResultadoCombate {
    GANA("Victoria"), PACTO_DE_PAZ("Pacto de paz"), PIERDE("Derrota");

    private final String descripcion;
    ResultadoCombate(String d) { this.descripcion = d; }
    public String getDescripcion() { return descripcion; }
    public boolean destruyeALaNavePropia() { return this == PIERDE; }
}