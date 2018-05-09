package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;


import Controller.ControllerMainWindow;
import Controller.ControllerWindow;
import Model.Carta;
import Model.Comanda;

public class Vista extends JFrame {

    //els diferents panells
    private PanelAcces panelAcces;
    private CartaPanel cartaPanel;
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

        this.setSize(600, 330);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        this.setLocationRelativeTo(null);
    }

    /**
     * Registra el controlador al panell d'acces
     * @param c controller
     */

    public void registerController(ControllerMainWindow c, ControllerWindow window) {
        //Pasem el controlado a la resta de panells
        panelAcces.registerController(c);
        this.registraControladors(window);
    }

    public void activaPanellsCarta(Carta carta, ControllerMainWindow c) {

        
        cartaPanel = new CartaPanel();
        cartaPanel.getPag().setPlats(carta.getPlats());
        cartaPanel.registerController(c);
        this.jp1 = new JPanel();
        jp1.setBackground(Color.LIGHT_GRAY);
        this.getContentPane().add("BUIT", jp1);
        this.getContentPane().add("CARTA", cartaPanel);


    }

    public void activaPanellsComanda(Comanda comanda, ControllerMainWindow controller, Carta carta) {

        this.panelEstatComanda = new PanelEstatComanda(comanda);
        this.panelSortida = new PanelSortida(comanda);

        this.getContentPane().add("ESTAT COMANDA", panelEstatComanda);
        this.getContentPane().add("SORTIR", panelSortida);

        panelSortida.registerController(controller);
        cartaPanel = new CartaPanel();
        cartaPanel.getPag().setPlats(carta.getPlats());
        cartaPanel.registerController(controller);
        
    }

    public void creaMenu(ControllerMainWindow c) {

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
    

    public PanelSortida getPanelSortida() {
        return panelSortida;
    }

    public void modificaPanelEstatComanda(Comanda comanda) {
        this.panelEstatComanda = new PanelEstatComanda(comanda);
    }

    public void newDialog(String missatge){
        JOptionPane.showMessageDialog(this, missatge);
    }

    public void registraControladors (WindowListener windowListener){
        addWindowListener(windowListener);
    }
    
    public void actualitzaPanelEstatComanda(Comanda comanda){panelEstatComanda.actualitzaComanda(comanda);}

    public CartaPanel getCartaPanel() { return cartaPanel; }
}

