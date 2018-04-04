package View;

import Model.Comanda;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class PanelEstatComanda extends JPanel {

    private LinkedList<Comanda> comanda;
    private JScrollPane jspComanda;


    /**
     * Constructor amb parametres. crea el panell que mostra l'estat de la comanda
     * @param comanda la comanda de la reserva
     */

    public PanelEstatComanda(LinkedList<Comanda> comanda){

        this.comanda = comanda;
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createTitledBorder("Estat de la teva comanda"));
        jspComanda = new JScrollPane();
        jspComanda.setLayout(new GridLayout(comanda.size(), 1));
        JTextArea jtaAux;
        JTextArea jtaAux2;
        JPanel auxPanel;

        for (Comanda c: comanda){
            auxPanel = new JPanel();
            auxPanel.setLayout(new GridLayout(1,2));
            jtaAux = new JTextArea();
            jtaAux.setEditable(false);
            jtaAux2 = new JTextArea();
            jtaAux2.setEditable(false);
            jtaAux.setText(c.getNomPlat() + "\n" + c.getPreuPlat() );
            auxPanel.add(jtaAux);
            if(c.esServit()){
                jtaAux2.setText("Servit");
                jtaAux2.setFont(jtaAux2.getFont().deriveFont(Font.BOLD));
            }else{
                jtaAux2.setText("Pendent");
            }
            auxPanel.add(jtaAux2);
            jspComanda.add(auxPanel);
        }

        this.add(jspComanda, BorderLayout.CENTER);

    }

}
