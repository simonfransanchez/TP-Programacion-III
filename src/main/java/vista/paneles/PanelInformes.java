
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
    private int cantidadMostrada = -1;

    public PanelInformes(Asistente asistente) {
        this.asistente = asistente;

        setLayout(new BorderLayout());
        setBackground(EstiloNave.FONDO);
        setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        texto.setEditable(false);
        EstiloNave.estilizarArea(texto);

        JPanel encabezado = new JPanel(new BorderLayout());
        encabezado.setBackground(EstiloNave.PANEL);
        encabezado.setBorder(BorderFactory.createEmptyBorder(5, 8, 10, 8));

        encabezado.add(EstiloNave.titulo("INFORMES DE MISIÓN"), BorderLayout.WEST);

        add(encabezado, BorderLayout.NORTH);
        add(EstiloNave.scroll(texto), BorderLayout.CENTER);

        pintar(asistente.getInformes());
        asistente.agregarObservador(this);
    }

    @Override
    public void actualizar() {
        final List<Informe> informes = asistente.getInformes();
        SwingUtilities.invokeLater(() -> pintar(informes));
    }

    private void pintar(List<Informe> informes) {
        if (informes.size() == cantidadMostrada) return;

        cantidadMostrada = informes.size();

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < informes.size(); i++) {
            Informe inf = informes.get(i);

            sb.append("══════════════════════════════\n");
            sb.append("MISIÓN ").append(i + 1).append("\n");
            sb.append("══════════════════════════════\n");

            sb.append(inf.getMision()).append("\n");
            sb.append("RESULTADO: ")
                    .append(inf.isExitosa() ? "EXITOSA" : "FALLIDA")
                    .append("\n\n");

            sb.append("Combustible consumido: ")
                    .append(inf.getCombustibleConsumido()).append("\n");

            sb.append("Energía consumida: ")
                    .append(inf.getEnergiaConsumida()).append("\n");

            sb.append("Desgaste generado: ")
                    .append(inf.getDesgasteGenerado()).append("\n\n");

            sb.append("ESTADO FINAL\n");
            sb.append("Combustible: ").append(inf.getCombustibleFinal()).append("\n");
            sb.append("Energía: ").append(inf.getEnergiaFinal()).append("\n");
            sb.append("Desgaste: ").append(inf.getDesgasteFinal()).append("\n\n");

            if (!inf.getObservaciones().isEmpty()) {
                sb.append("OBSERVACIONES\n");
                sb.append(String.join("\n", inf.getObservaciones()));
                sb.append("\n\n");
            }
        }

        texto.setText(sb.toString());
        texto.setCaretPosition(texto.getDocument().getLength());
    }
}
