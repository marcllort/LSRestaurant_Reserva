package View;
//import Controller.Controller;
import Model.Comanda;
import Model.Plat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class PanelSortida extends JPanel {

    private JLabel jlPreu;
    private JButton jbMartxar;
    private float preu;
    private ArrayList<Comanda> comanda;
    private DialogSortida dialogSortida;
    /**
     * Constructor amb parametres per crear el panell de sortida
     * @param comanda una llista de la comanda de la reserva per tal de calcular el preu final, donada per la BBDD
     */
    public PanelSortida(ArrayList<Comanda> comanda){

        this.setLayout(new BorderLayout());
        preu = 0;
        this.comanda = comanda;

        for(Comanda c: comanda){
            for(Plat p: c.getPlats()){
                preu += p.getPreu();
            }
        }
        jlPreu = new JLabel("El preu a pagar és " + preu + "€");
        jbMartxar= new JButton("Pagar");
        JPanel jpAux = new JPanel();
        jpAux.setLayout(new BoxLayout(jpAux, BoxLayout.Y_AXIS));
        jpAux.add(jlPreu);
        jpAux.add(jbMartxar);
        this.add(jpAux, BorderLayout.CENTER);
    }


    public void registerController(ActionListener controlador){

        jbMartxar.addActionListener(controlador);
        jbMartxar.setActionCommand("SORTIR");
    }

    public float getPreuAPagar(){return preu;}

    public void dialogSortida(ActionListener controlador){
        dialogSortida = new DialogSortida(comanda);
        dialogSortida.registerController(controlador);
        dialogSortida.setVisible(true);
    }

    public void desactivaDialogSortida(){
        dialogSortida.setVisible(false);
    }
}
