package naves;

public class NaveExploradora extends NaveBase {

    public NaveExploradora() {
        super(60, 80);
    }
    public String toString(){
        return super.toString()+"  Tipo: Exploradora";
    }

    @Override public ResultadoCombate combatirContra(NaveBase e) { return e.resultadoParaExploradora(); }
    @Override public ResultadoCombate resultadoParaExploradora() { return ResultadoCombate.PACTO_DE_PAZ; }
    @Override public ResultadoCombate resultadoParaCarguero()    { return ResultadoCombate.PIERDE; }
    @Override public ResultadoCombate resultadoParaCombate()     { return ResultadoCombate.GANA; }


}