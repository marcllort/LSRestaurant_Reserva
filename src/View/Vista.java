package View;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

import Controller.Controller;
import Model.Plat;
import Model.Carta;
import Model.Comanda;

public class Vista extends JFrame {

    //els diferents panells
    private PanelAcces panelAcces;
    private PanelCarta panelCarta;
    private PanelEstatComanda panelEstatComanda;
    private PanelSortida panelSortida;
    private JPanel jp1;
    //Layout per tal de mostrar un conjunt de panells
    private CardLayout layout;
    //menu
    private JMenuBar jmbMenu;
    private JMenuItem jmiCarta;
    private JMenuItem jmiComanda;
    private JMenuItem jmiEstatComanda;
    private JMenuItem jmiPagar;



    /**
     * Constructor sense parametres.
     * Crea la finestra que permetra visualitzar els diferents panells
     */
    public Vista(){
        layout = new CardLayout();
        this.panelAcces = new PanelAcces();
        this.getContentPane().setLayout(layout);
        this.getContentPane().add("ACCES", panelAcces);


        this.setSize(400, 400);
        this.setResizable(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

    }


    /**
     * Registra el controlador al menu i a la resta de panells
     */

    public void registerController(Controller c){
        //Pasem el controlado a la resta de panells
        panelAcces.registerController(c);


    }


    public void activaPanells(ArrayList<Comanda> comanda, Carta carta, Controller c){

        this.panelCarta = new PanelCarta(carta);
        this.panelEstatComanda = new PanelEstatComanda(comanda);
        this.panelSortida = new PanelSortida(comanda);
        this.jp1 = new JPanel();
        jp1.setBackground(Color.LIGHT_GRAY);

        this.getContentPane().add("BUIT", jp1);
        this.getContentPane().add("CARTA", panelCarta);
        this.getContentPane().add("ESTAT COMANDA", panelEstatComanda);
        this.getContentPane().add("SORTIR", panelSortida);

        creaMenu();


        creaMenu();

        // Registrem controlador a les diferents opcions del menu
        jmiCarta.addActionListener(c);
        jmiCarta.setActionCommand("ACCES CARTA");
        jmiEstatComanda.addActionListener(c);
        jmiEstatComanda.setActionCommand("ACCES ESTAT COMANDA");
        jmiComanda.addActionListener(c);
        jmiComanda.setActionCommand("ACCES EDITOR COMANDA");
        jmiPagar.addActionListener(c);
        jmiPagar.setActionCommand("ACCES SORTIDA");

        //Registrem el controlador als diferents panells
        panelCarta.registerController(c);
        panelSortida.registerController(c);


    }

    private void creaMenu(){
        jmbMenu = new JMenuBar();
        jmiCarta = new JMenuItem("Carta");
        jmiComanda = new JMenuItem("Editar Comanda");
        jmiEstatComanda = new JMenuItem("Estat  Comanda");
        jmiPagar = new JMenuItem("Pagar i  Sortir");
        jmbMenu.setBackground(Color.LIGHT_GRAY);
        jmbMenu.setBorderPainted(true);
        jmbMenu.add(jmiCarta);
        jmbMenu.add(jmiComanda);
        jmbMenu.add(jmiEstatComanda);
        jmbMenu.add(jmiPagar);
        this.setJMenuBar(jmbMenu);

    }



    public String getTypedUsuari(){return panelAcces.getTypedUsuari();}

    public String getTypedContrasenya(){return panelAcces.getTypedContrasenya();}

    public void cleanFields(){panelAcces.cleanFields();}

    public void changePanel(String quin){ layout.show(this.getContentPane(), quin);}

    public PanelCarta getPanelCarta(){return panelCarta;}

    public PanelSortida getPanelSortida(){return panelSortida;}

    public void modificaPanelEstatComanda(ArrayList<Comanda> comanda){
        this.panelEstatComanda = new PanelEstatComanda(comanda);
    }
}

