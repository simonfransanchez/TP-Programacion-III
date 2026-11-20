package vista.paneles;

import asistente.Asistente;
import destinoytablero.Observador;
import misiones.Informe;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PanelInformes extends JPanel implements Observador {
    private final Asistente asistente;
    private final JTextArea texto = new JTextArea(8, 30);
    private int cantidadMostrada = -1;                 // solo se usa desde el hilo de Swing

    public PanelInformes(Asistente asistente) {
        this.asistente = asistente;
        texto.setEditable(false);
        texto.setLineWrap(true);
        texto.setWrapStyleWord(true);
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Informes de misiones"));
        add(new JScrollPane(texto), BorderLayout.CENTER);

        pintar(asistente.getInformes());
        asistente.agregarObservador(this);
    }

    @Override
    public void actualizar() {
        final List<Informe> informes = asistente.getInformes();
        SwingUtilities.invokeLater(() -> pintar(informes));
    }

    private void pintar(List<Informe> informes) {
        // El asistente también avisa cuando cambian los recursos: solo se redibuja si hay un informe nuevo
        if (informes.size() == cantidadMostrada) return;
        cantidadMostrada = informes.size();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < informes.size(); i++) {
            Informe inf = informes.get(i);
            sb.append(i + 1).append(". ").append(inf.getMision())
                    .append(inf.isExitosa() ? " - EXITOSA" : " - FALLIDA").append('\n')
                    .append("   Combustible consumido: ").append(inf.getCombustibleConsumido()).append('\n')
                    .append("   Energía consumida: ").append(inf.getEnergiaConsumida()).append('\n')
                    .append("   Desgaste generado: ").append(inf.getDesgasteGenerado()).append('\n')
                    .append("   Estado final: comb ").append(inf.getCombustibleFinal())
                    .append(", energía ").append(inf.getEnergiaFinal())
                    .append(", desgaste ").append(inf.getDesgasteFinal()).append('\n');
            if (!inf.getObservaciones().isEmpty()) {
                sb.append("   Observaciones: ").append(String.join("; ", inf.getObservaciones())).append('\n');
            }
            sb.append('\n');
        }
        texto.setText(sb.toString());
        texto.setCaretPosition(texto.getDocument().getLength());
    }
}
