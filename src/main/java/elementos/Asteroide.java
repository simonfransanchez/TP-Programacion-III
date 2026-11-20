package elementos;

import asistente.Asistente;

public class Asteroide implements ElementoDelUniverso {
    private final int danioEnergia;

    public Asteroide(int danioEnergia) {
        if (danioEnergia <= 0) throw new IllegalArgumentException("El daño debe ser positivo");
        this.danioEnergia = danioEnergia;
    }

    @Override public void interactuar(Asistente asistente) {
        asistente.registrar("Impacto con asteroide: -" + danioEnergia + " de energía");
        asistente.perderEnergia(danioEnergia);
    }
    @Override public String descripcion() { return "Asteroide"; }
    @Override public boolean esObstaculo() { return true; }
}