package motor;

public class EnWarpState {
    private Motor m;

    public DisponibleState(Motor mm){       //esto linkea el motor con esta clase
        this.m=mm;
    }


    public void terminarWarp(){
        System.out.println("Se termino el warp");
        m.setEstado(new EnfriandoState(m));        //aca cambio el estado a preparando
    }

}
