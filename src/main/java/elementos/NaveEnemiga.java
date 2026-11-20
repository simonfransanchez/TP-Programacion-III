package elementos;

import asistente.Asistente;
import naves.NaveBase;
import naves.ResultadoCombate;

import java.util.Objects;

public class NaveEnemiga implements ElementoDelUniverso {
    private final NaveBase nave;

    public NaveEnemiga(NaveBase nave) { this.nave = Objects.requireNonNull(nave); }

    @Override public void interactuar(Asistente asistente) {
        ResultadoCombate r = asistente.combatirContra(nave);
        asistente.registrar("Encuentro con " + descripcion() + ": " + r.getDescripcion());
        if (r.destruyeALaNavePropia()) {
            asistente.destruirNave("derrota en combate");
        }
    }
    @Override public String descripcion() { return "Nave enemiga (" + nave.getClass().getSimpleName() + ")"; }
}