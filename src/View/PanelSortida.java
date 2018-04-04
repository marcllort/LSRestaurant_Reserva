package View;

import Model.Comanda;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class PanelSortida extends JPanel {

    private JLabel jlPreu;
    private JButton jbMartxar;
    private float preu;

    /**
     * Constructor amb parametres per crear el panell de sortida
     * @param comanda una llista de la comanda de la reserva per tal de calcular el preu final
     */
    public PanelSortida(LinkedList<Comanda> comanda){

        this.setLayout(new BorderLayout());
        preu = 0;

        for(Comanda c : comanda){
            preu += c.getPreuPlat();
        }


    }
}
