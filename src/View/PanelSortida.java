package View;

import Controller.Controller;
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

    /**
     * Constructor amb parametres per crear el panell de sortida
     * @param comanda una llista de la comanda de la reserva per tal de calcular el preu final, donada per la BBDD
     */
    public PanelSortida(ArrayList<Comanda> comanda){

        this.setLayout(new BorderLayout());
        preu = 0;

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

    public void mostraDialog(Controller c){
        JDialog jdMissatge = new JDialog();
        jdMissatge.setLayout(new BorderLayout());
        JLabel jl = new JLabel();
        jl.setText("Encara no s'han servit tots els plats. \n Segur que vols martxar?");
        JButton jb1 = new JButton("Si");
        JButton jb2 = new JButton("No");
        JPanel jpAux = new JPanel(new FlowLayout());
        jpAux.add(jb1);
        jpAux.add(jb2);
        jdMissatge.add(jpAux, BorderLayout.PAGE_END);
        jdMissatge.add(jl, BorderLayout.CENTER);

        //controlador

        jb1.addActionListener(c);
        jb1.setActionCommand("SI");
        jb2.addActionListener(c);
        jb2.setActionCommand("NO");

    }

    public void registerController(ActionListener controlador){

        jbMartxar.addActionListener(controlador);
        jbMartxar.setActionCommand("SORTIR");
    }
}
