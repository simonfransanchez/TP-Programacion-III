package elementos;

import asistente.Asistente;

public class EstacionDeCarga implements ElementoDelUniverso {
    private final int combustible, energia;

    public EstacionDeCarga(int combustible, int energia) {
        if (combustible < 0 || energia < 0) throw new IllegalArgumentException("Montos inválidos");
        this.combustible = combustible;
        this.energia = energia;
    }

    @Override public void interactuar(Asistente asistente) {
        asistente.registrar("Estación de carga: +" + combustible + " combustible, +" + energia + " energía");
        asistente.recargar(combustible,energia);
    }
    @Override public String descripcion() { return "Estación de carga"; }
}