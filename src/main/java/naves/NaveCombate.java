package naves;

public class NaveCombate extends NaveBase {

    public NaveCombate() {
        super(80, 100);
    }
    public String toString(){
        return super.toString()+"  Tipo: Combate";
    }

    @Override public ResultadoCombate combatirContra(NaveBase e) { return e.resultadoParaCombate(); }
    @Override public ResultadoCombate resultadoParaExploradora() { return ResultadoCombate.PIERDE; }
    @Override public ResultadoCombate resultadoParaCarguero()    { return ResultadoCombate.GANA; }
    @Override public ResultadoCombate resultadoParaCombate()     { return ResultadoCombate.PACTO_DE_PAZ; }
}