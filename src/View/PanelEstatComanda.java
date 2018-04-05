package View;

import Model.Comanda;
import Model.Plat;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class PanelEstatComanda extends JPanel {


    //Ens la dona la BBDD
    private ArrayList<Comanda> comanda;
    private JScrollPane jspComanda;


    /**
     * Constructor amb parametres. crea el panell que mostra l'estat de la comanda
     * @param comanda la comanda de la reserva
     */

    public PanelEstatComanda(ArrayList<Comanda> comanda){

        this.comanda = comanda;
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createTitledBorder("Estat de la teva comanda"));
        jspComanda = new JScrollPane();
        int aux = 0;
        for(Comanda c: comanda){
            aux += c.getPlats().size();
        }
        jspComanda.setLayout(new GridLayout(aux, 1));
        JTextArea jtaAux;
        JTextArea jtaAux2;
        JPanel auxPanel;

       for(Comanda c: comanda){
           jtaAux2 = new JTextArea();
           jtaAux2.setEditable(false);
           if(c.esServit()){
               jtaAux2.setText("Servit");
               jtaAux2.setFont(jtaAux2.getFont().deriveFont(Font.BOLD));
           }else {
               jtaAux2.setText("Pendent...");
           }
           for(Plat p : c.getPlats()){
               auxPanel = new JPanel();
               auxPanel.setLayout(new GridLayout(1,2));
               jtaAux = new JTextArea();
               jtaAux.setEditable(false);
               jtaAux.setText(p.getNomPlat() + " \n" + p.getPreu() + "€");
               auxPanel.add(jtaAux);
               auxPanel.add(jtaAux2);
               jspComanda.add(auxPanel);
           }


       }
    }

}
