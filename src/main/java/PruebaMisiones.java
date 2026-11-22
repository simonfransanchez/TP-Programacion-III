import asistente.Asistente;
import destinoytablero.Posicion;
import destinoytablero.Tablero;
import elementos.*;
import misiones.*;
import naves.*;

import java.util.ArrayList;

public class PruebaMisiones {

    static int fallosDeInvariante = 0;

    static void verificar(boolean condicion, String mensaje) {
        if (!condicion) { fallosDeInvariante++; System.out.println("  [FALLA] " + mensaje); }
    }

    static Tablero mundo(int asteroides, int danio, int estaciones, int enemigas) throws Exception {
        ArrayList<ElementoDelUniverso> e = new ArrayList<ElementoDelUniverso>();
        for (int i = 0; i < asteroides; i++) e.add(new Asteroide(danio));
        for (int i = 0; i < estaciones; i++) e.add(new EstacionDeCarga(10, 10));
        String[] tipos = {"Exploradora", "Carguero", "Combate"};
        for (int i = 0; i < enemigas; i++) e.add(new NaveEnemiga(NaveFactory.getNave(tipos[i % 3])));
        return new Tablero(10, 10, e);
    }

    static void invariantes(Asistente a, String donde) {
        verificar(a.getCombustible() >= 0 && a.getCombustible() <= 100, donde + ": combustible fuera de rango " + a.getCombustible());
        verificar(a.getEnergia() >= 0 && a.getEnergia() <= 100, donde + ": energia fuera de rango " + a.getEnergia());
        verificar(a.getDesgaste() >= 0 && a.getDesgaste() <= 100, donde + ": desgaste fuera de rango " + a.getDesgaste());
        verificar(a.getGemas() >= 0, donde + ": gemas negativas");
    }

    public static void main(String[] args) throws Exception {
        // ---------- 1. Un recorrido completo, con bitacora e informes ----------
        System.out.println("===== 1. M-01, M-02 y M-03 sobre el mismo asistente =====");
        Tablero t = mundo(8, 10, 2, 3);
        NaveBase nave = NaveFactory.getNave("Combate");
        Asistente asistente = new Asistente(nave, t);
        Mision m1 = new Mision01(asistente, 10, 10); m1.setPausaMs(0);
        Mision m2 = new Mision02(asistente, 40);     m2.setPausaMs(0);
        Mision m3 = new Mision03(asistente);         m3.setPausaMs(0);
        for (Mision m : new Mision[]{m1, m2, m3}) {
            Informe i = m.realizarMision();
            System.out.println(i);
            System.out.println("  nave: " + nave + " | posicion " + asistente.getPosicion().getX() + "," + asistente.getPosicion().getY());
            invariantes(asistente, m.nombreMision());
        }
        System.out.println("--- bitacora ---");
        asistente.mostrarBitacora();

        // ---------- 2. Rechazo: no cambia nada ----------
        System.out.println("\n===== 2. Rechazo por combustible =====");
        NaveBase pobre = NaveFactory.getNave("Exploradora");
        Asistente ap = new Asistente(pobre, mundo(4, 10, 1, 1));
        ap.setCombustible(4);
        String antes = pobre.toString();
        Informe ir = new Mision01(ap, 5, 5).realizarMision();
        System.out.println(ir);
        verificar(!ir.isExitosa(), "una mision rechazada no puede ser exitosa");
        verificar(antes.equals(pobre.toString()), "el rechazo modifico la nave: " + antes + " -> " + pobre);
        verificar(!ir.getObservaciones().isEmpty(), "el rechazo no dejo el motivo");

        // ---------- 3. Reutilizar una mision ----------
        System.out.println("\n===== 3. Misma instancia dos veces =====");
        NaveBase n3 = NaveFactory.getNave("Carguero");
        Asistente a3 = new Asistente(n3, mundo(4, 10, 1, 1));
        Mision una = new Mision02(a3, 30); una.setPausaMs(0);
        una.realizarMision();
        try { una.realizarMision(); verificar(false, "debio lanzar IllegalStateException"); }
        catch (IllegalStateException e) { System.out.println("OK: " + e.getMessage()); }

        // ---------- 4. Asistente sin tablero ----------
        System.out.println("\n===== 4. Asistente sin tablero (como en la Parte 1) =====");
        NaveBase n4 = NaveFactory.getNave("Exploradora");
        Informe i4 = new Mision03(new Asistente(n4)).realizarMision();
        System.out.println(i4.getObservaciones());
        verificar(!i4.isExitosa(), "sin tablero no puede haber exito");

        // ---------- 5. Muchos mundos aleatorios ----------
        System.out.println("\n===== 5. 300 mundos aleatorios, M-01 -> M-02 -> M-03 =====");
        int[] exitos = new int[3], rechazos = new int[3], fallos = new int[3];
        int destruidas = 0, conWarp = 0, recalculos = 0, sinExcepcion = 0;
        for (int k = 0; k < 300; k++) {
            int asteroides = 5 + (k % 20);
            NaveBase nv = NaveFactory.getNave(new String[]{"Exploradora", "Carguero", "Combate"}[k % 3]);
            Tablero tb = mundo(asteroides, 10 + (k % 4) * 10, 2, 3);
            Asistente as = new Asistente(nv, tb);
            Mision[] ms = { new Mision01(as), new Mision02(as), new Mision03(as) };
            for (int j = 0; j < 3; j++) {
                ms[j].setPausaMs(0);
                int gemasAntes = as.getGemas();
                Informe inf = ms[j].realizarMision();
                invariantes(as, "mundo " + k + " " + ms[j].nombreMision());
                verificar(inf != null, "informe null");
                if (inf.isExitosa()) {
                    exitos[j]++;
                    if (j == 1) verificar(as.getGemas() >= gemasAntes + 20, "M-02 exitosa sin gemas");
                    if (j == 2) {
                        verificar(as.getPosicion().equals(as.getBase()), "M-03 exitosa fuera de la base");
                        verificar(as.getCombustible() == 100 && as.getEnergia() == 100 && as.getDesgaste() == 0, "M-03 no recargo/reparo: " + nv);
                    }
                } else if (inf.getRecorrido().isEmpty()) rechazos[j]++;
                else fallos[j]++;
                if (inf.getAcciones().contains("Ruta recalculada")) recalculos++;
                for (String ev : inf.getEventosRelevantes()) if (ev.contains("warp")) { conWarp++; break; }
                if (!as.naveViva()) { destruidas++; break; }
            }
            sinExcepcion++;
        }
        System.out.println("Mundos terminados sin excepciones: " + sinExcepcion + "/300");
        for (int j = 0; j < 3; j++)
            System.out.println("M-0" + (j + 1) + ": exitosas " + exitos[j] + ", rechazadas " + rechazos[j] + ", fallidas en viaje " + fallos[j]);
        System.out.println("Naves destruidas: " + destruidas + " | misiones con salto warp: " + conWarp + " | con ruta recalculada: " + recalculos);

        System.out.println("\n===== RESULTADO: " + (fallosDeInvariante == 0 ? "sin fallas de invariantes" : fallosDeInvariante + " FALLAS") + " =====");
    }
}