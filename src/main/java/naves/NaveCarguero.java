package naves;

public class NaveCarguero extends NaveBase {

    public NaveCarguero() {
        super(100, 60);
    }
    public String toString(){
        return super.toString()+"  Tipo: Carguero";
    }

    @Override public ResultadoCombate combatirContra(NaveBase e) { return e.resultadoParaCarguero(); }
    @Override public ResultadoCombate resultadoParaExploradora() { return ResultadoCombate.GANA; }
    @Override public ResultadoCombate resultadoParaCarguero()    { return ResultadoCombate.PACTO_DE_PAZ; }
    @Override public ResultadoCombate resultadoParaCombate()     { return ResultadoCombate.PIERDE; }

}