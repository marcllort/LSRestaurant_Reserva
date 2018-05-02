package View;

import Controller.ControllerMainWindow;
import Model.Plat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PlatsPanel extends JPanel {

    private ArrayList<Plat> plats;
    private int numPagina;
    private ArrayList<JRadioButton> jrButtons;

    /**
     * Constructor del panell amb parametres
     * @param plats els plats a afegir al panell
     * @param numPagina de quina pagina es tracta
     */

    public PlatsPanel(ArrayList<Plat> plats, int numPagina){
        this.plats = plats;
        this.numPagina = numPagina;
        jrButtons = creaButtons();
        this.setLayout(new GridLayout(2, 3));
        for (JRadioButton jb: jrButtons){
            this.add(jb);
        }

    }

    /**
     * funcio que crea els botons de la carta
     * @return arrayList de botons
     */

    private ArrayList<JRadioButton> creaButtons(){
        ArrayList<JRadioButton> array = new ArrayList<JRadioButton>();
        JRadioButton boton;
        for(Plat p: plats){
            boton = new JRadioButton(p.getNomPlat() + "\n" + p.getPreu());
            array.add(boton);
        }
        return array;
    }

    /**
     * Registrem el controlador als diferents botons
     * @param c controller
     */

    public void registerController(ActionListener c){
        int i = 0;
        for (JRadioButton jb: jrButtons){
            i++;
            jb.addActionListener(c);
            jb.setActionCommand( i + "-" + numPagina);

        }
    }

    /**
     * Getter que retorna els plats de la pagina
     * @return plats
     */

    public ArrayList<Plat> getPlats() {
        return plats;
    }
}
