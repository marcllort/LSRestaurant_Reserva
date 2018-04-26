package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowListener;


import Controller.Controller;
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

    public Vista() {

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

    public void registerController(Controller c) {
        //Pasem el controlado a la resta de panells
        panelAcces.registerController(c);
    }

    public void activaPanellsCarta(Carta carta, Controller c) {

        this.panelCarta = new PanelCarta(carta);

        this.jp1 = new JPanel();
        jp1.setBackground(Color.LIGHT_GRAY);

        this.getContentPane().add("BUIT", jp1);
        this.getContentPane().add("CARTA", panelCarta);



        //Registrem el controlador als diferents panells
        panelCarta.registerController(c);

    }

    public void activaPanellsComanda(Comanda comanda, Controller controller, Carta carta) {

        this.panelEstatComanda = new PanelEstatComanda(comanda);
        this.panelSortida = new PanelSortida(comanda);

        this.getContentPane().add("ESTAT COMANDA", panelEstatComanda);
        this.getContentPane().add("SORTIR", panelSortida);

        panelSortida.registerController(controller);


        this.panelCarta = new PanelCarta(carta);


        //Registrem el controlador als diferents panells
        panelCarta.registerController(controller);



    }

    public void creaMenu(Controller c) {

        jmiCarta = new JMenuItem("Carta");
        jmiComanda = new JMenuItem("Editar Comanda");
        jmiEstatComanda = new JMenuItem("Estat  Comanda");
        jmiPagar = new JMenuItem("Pagar i  Sortir");

        jmbMenu = new JMenuBar();
        jmbMenu.setBackground(Color.LIGHT_GRAY);
        jmbMenu.setBorderPainted(true);
        jmbMenu.add(jmiCarta);
        jmbMenu.add(jmiComanda);
        jmbMenu.add(jmiEstatComanda);
        jmbMenu.add(jmiPagar);

        this.setJMenuBar(jmbMenu);

        // Registrem controlador a les diferents opcions del menu
        jmiCarta.addActionListener(c);

        jmiCarta.setActionCommand("ACCES CARTA");
        jmiEstatComanda.addActionListener(c);
        jmiEstatComanda.setActionCommand("ACCES ESTAT COMANDA");

        jmiComanda.setActionCommand("ACCES EDITOR COMANDA");
        jmiComanda.addActionListener(c);
        jmiPagar.addActionListener(c);
        jmiPagar.setActionCommand("ACCES SORTIDA");

    }

    public String getTypedUsuari() {
        return panelAcces.getTypedUsuari();
    }

    public String getTypedContrasenya() {
        return panelAcces.getTypedContrasenya();
    }

    public void cleanFields() {
        panelAcces.cleanFields();
    }

    public void changePanel(String quin) {
        layout.show(this.getContentPane(), quin);
    }

    public PanelCarta getPanelCarta() {
        return panelCarta;
    }

    public PanelSortida getPanelSortida() {
        return panelSortida;
    }

    public void modificaPanelEstatComanda(Comanda comanda) {
        this.panelEstatComanda = new PanelEstatComanda(comanda);
    }

    public void registraControladors (WindowListener windowListener){
        addWindowListener(windowListener);
    }

}

