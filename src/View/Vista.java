package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;


import Controller.ControllerMainWindow;
import Controller.ControllerWindow;
import Model.Carta;
import Model.Comanda;

public class Vista extends JFrame {

    //PANELS
    private PanelAcces panelAcces;
    private CartaPanel cartaPanel;
    private PanelEstatComanda panelEstatComanda;
    private PanelSortida panelSortida;

    //Layout per tal de mostrar un conjunt de panells
    private CardLayout layout;


    /**
     * Constructor sense parametres.
     * Crea la finestra amb un GardLayout que permetra visualitzar els diferents panells
     */
    public Vista() {

        layout = new CardLayout();
        setTitle("LSRestaurant-Client");

        this.panelAcces = new PanelAcces();
        this.getContentPane().setLayout(layout);
        this.getContentPane().add("ACCES", panelAcces);

        this.setSize(600, 330);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        this.setLocationRelativeTo(null);
        cartaPanel = new CartaPanel();

    }

    /**
     * Funcio que crea el menu de la vista principal.
     * També registra el controlador als ítems del menú.
     *
     * @param c Controlador
     */
    public void creaMenu(ControllerMainWindow c) {

        JMenuItem jmiCarta = new JMenuItem("Carta");
        JMenuItem jmiComanda = new JMenuItem("Editar Comanda");
        JMenuItem jmiEstatComanda = new JMenuItem("Estat  Comanda");
        JMenuItem jmiPagar = new JMenuItem("Pagar i  Sortir");

        //menu
        JMenuBar jmbMenu = new JMenuBar();
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

    /**
     * Funcio que registra a la finestra principal un Window Listener
     *
     * @param windowListener el controlador
     */

    public void registraControladors(WindowListener windowListener) {
        addWindowListener(windowListener);
    }

    /**
     * Registra els controlador
     *
     * @param c      Controlador Vista principal
     * @param window controlador de la finestra
     */
    public void registerController(ControllerMainWindow c, ControllerWindow window) {
        //Pasem el controlado a la resta de panells
        panelAcces.registerController(c);
        this.registraControladors(window);
    }

    /**
     * Activa aquells panels de la vista principal que depenen de la carta i registra el controlador
     *
     * @param carta La carta del restaurant
     * @param c     Controlador per tal de registrar-lo en els panells
     */
    public void activaPanellsCarta(Carta carta, ControllerMainWindow c) {

        cartaPanel.getPag().setPlats(carta.getPlats());
        cartaPanel.registerController(c);
        this.getContentPane().add("CARTA", cartaPanel);

    }

    /**
     * Activa aquells panells de la vista principal que depenen de la comanda i registra el controlador en ells
     *
     * @param comanda    La comanda del usuari
     * @param controller Controlador per tal de registrar-lo en els panells
     * @param carta      La carta del restaurant
     */
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

    /**
     * Funcio que nateja els camps d'acces.
     */
    public void cleanFields() {
        panelAcces.cleanFields();
    }

    /**
     * L'encarregat de canviar el panell que es mostra.
     * Es canvia en funcio de l'opcio del menu escollida
     *
     * @param quin A quin panell es vol canviar
     */
    public void changePanel(String quin) {
        layout.show(this.getContentPane(), quin);
    }

    /**
     * Crea un dialog per tal de mostrar un missatge per pantalla
     * @param missatge quin missatge volem mostrar
     */
    public void newDialog(String missatge) {
        JOptionPane.showMessageDialog(this, missatge);
    }

    /**
     * S'encarrega de actualitzar la vista que conte la carta
     */
    public void actualitzaVistaCarta() {
        this.remove(cartaPanel);
        this.getContentPane().add("CARTA", cartaPanel);
        changePanel("CARTA");

    }

    /**
     * S'encarrega de actualitzar els plats que conte la carta
     * @param carta Carta actualitzada
     * @param controlador per registrar-lo a els botons
     */
    public void actualitzaPlatsVistaCarta(Carta carta, ActionListener controlador) {
        this.remove(cartaPanel);
        cartaPanel.getPag().setPlats(carta.getPlats());
        cartaPanel.getPag().registraControler(controlador);
        this.getContentPane().add("CARTA", cartaPanel);
        changePanel("CARTA");
    }


    /**
     * Funcio que retorna l'usuari escrit del panell d'acces
     *
     * @return l'usuari escrit
     */
    public String getTypedUsuari() {
        return panelAcces.getTypedUsuari();
    }

    /**
     * Funcio que retorna la contrasenya de l'usuari escrita en el panell d'acces
     *
     * @return la contrasenya de l'usuari
     */
    public String getTypedContrasenya() {
        return panelAcces.getTypedContrasenya();
    }

    /**
     * Getter del Panell de Sortida
     *
     * @return el panell de sortida
     */
    public PanelSortida getPanelSortida() {
        return panelSortida;
    }



    /**
     * Encarregar de modificar el panell de l'estat de la comanda,
     * ja sigui per si s'han servit plats o s'ha afegit una comanda nova
     *
     * @param comanda la comanda actualitzada
     */
    public void actualitzaPanelEstatComanda(Comanda comanda) {
        panelEstatComanda.actualitzaComanda(comanda);
    }


    /**
     * Getter que retorna el panell de la carta
     *
     * @return el panell de la carta
     */
    public CartaPanel getCartaPanel() {
        return cartaPanel;
    }
}

