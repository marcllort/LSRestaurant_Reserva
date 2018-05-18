package Controller;


import Model.Carta;
import Model.Comanda;
import Model.Plat;
import Model.Usuari;
import NetworkManager.ServerConnect;
import View.DialogPlat;
import View.Vista;
import View.VistaEditorComanda;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;


public class ControllerMainWindow implements ActionListener {
    //VISTA
    private Vista view;
    private VistaEditorComanda viewComanda;
    private DialogPlat dp;
    //MODEL
    private Carta carta;
    private Comanda comanda;
    private Comanda comandaActual;
    private ServerConnect serverConnect;
    //CONTROLADOR EDITOR COMANDA
    private ControllerViewComanda controllerViewComanda;
    private String card;

    /**
     * Constructor amb parametres del controlador de la finestra principal
     *
     * @param view          vista principal
     * @param serverConnect connexio amb el servidor
     */
    public ControllerMainWindow(Vista view, ServerConnect serverConnect) {
        this.view = view;
        this.serverConnect = serverConnect;
        carta = new Carta();
        view.creaMenu(this);
        comandaActual = new Comanda();
        viewComanda = new VistaEditorComanda(comandaActual);
        viewComanda.setVisible(false);
    }


    /**
     * Gestiona els esdeveniments
     *
     * @param event Font de l'esdeveniment
     */
    public void actionPerformed(ActionEvent event) {
        //Si es tracta d'una accio sobre el menu

        if (event.getSource() instanceof JMenuItem) {
            handleMenu(event);
        } else {
            //Si es tracta d'alguna opció sobre els panells
            handlePanels(event);
        }
        //Si es tracta d'una accio sobre el JDialog de sortida
        if (event.getActionCommand().equals("SORTIR PROGRAMA")) {
            //Vista torna a ser la del principi
            handleSortida();

        } else if (event.getActionCommand().equals("QUEDAR-SE")) {
            view.changePanel("BUIT");
        }
    }

    /**
     * Comprova que les credencials enviades per l'usuari siguin correctes
     */
    private void handleAcces() {

        String usuari = view.getTypedUsuari();
        String contrasenya = view.getTypedContrasenya();
        if (usuari.equals("") || contrasenya.equals("")) {
            JOptionPane.showMessageDialog(view, "Has d'omplir els espais d'usuari i contrasenya");
            view.cleanFields();
        } else {
            serverConnect.enviaUser(new Usuari(usuari, contrasenya));
            String userConfirmation = serverConnect.repUserConfirmation();
            if (userConfirmation.equals("true")) {
                JOptionPane.showMessageDialog(view, "Benvingut!");
                view.activaPanellsCarta(carta, this);
                view.actualitzaVistaCarta();
                view.setSize(800, 500);
                view.setResizable(true);
                serverConnect.startServerConnection(this);

            } else {
                JOptionPane.showMessageDialog(view, "Credencials incorrectes!");
                view.cleanFields();
            }
        }
    }

    /**
     * S'encarrega de sortir del programa
     */

    public void handleSortida() {
        view.cleanFields();
        this.view = new Vista();
        view.setVisible(true);
        // view.registerController(this);
    }

    /**
     * S'encarrega d'obrir la nova ventana per poder editar la comanda
     *
     * @param viewComanda2 la vista de la comanda
     */
    public void handleVistaComanda(VistaEditorComanda viewComanda2) {

        if (comandaActual.getPlats().size() == 0) {
            viewComanda.setVisible(false);
            JOptionPane.showMessageDialog(view, "Afegeix plats per tal d'editar la teva comanda");

        } else {

            viewComanda = new VistaEditorComanda(comandaActual);
            viewComanda.setVisible(true);
            viewComanda.setLocationRelativeTo(view);
            preparaComanda();
            controllerViewComanda = new ControllerViewComanda(serverConnect, comandaActual, viewComanda, this);
            viewComanda.registerController(controllerViewComanda);
            view.actualitzaPanelEstatComanda(comanda);
        }

    }

    /**
     * S'encarrega de controlar quan s'afegeix un plat
     *
     * @param event font de l'esdeveniment
     */

    private void handleAfegeixPlat(ActionEvent event) {

        handleDialogPlat(carta.getPlat(event.getActionCommand()), event);
        if (viewComanda.isVisible()) {
            viewComanda.dispatchEvent(new WindowEvent(viewComanda, WindowEvent.WINDOW_CLOSING));

            this.handleVistaComanda(viewComanda);

        }
    }

    /**
     * Gestiona els esdeveniments sobre el menu
     *
     * @param event Font de l'esdeveniment
     */
    private void handleMenu(ActionEvent event) {

        if (event.getActionCommand().equals("ACCES CARTA")) {
            card = "CARTA";
            view.changePanel("CARTA");
        } else if (event.getActionCommand().equals("ACCES ESTAT COMANDA")) {
            card = "ESTAT COMANDA";
            view.changePanel("ESTAT COMANDA");
        } else if (event.getActionCommand().equals("ACCES SORTIDA")) {
            card = "SORTIR";
            view.changePanel("SORTIR");
        } else if (event.getActionCommand().equals("ACCES EDITOR COMANDA")) {
            card = "EDITOR";
            viewComanda.setVisible(false);
            viewComanda.dispose();
            handleVistaComanda(viewComanda);
        }

    }

    /**
     * Controla els esdeveniments sobre els panells
     *
     * @param event font de l'esdeveniment
     */
    private void handlePanels(ActionEvent event) {

        //accio sobre el panell d'acces
        if (event.getActionCommand().equals("ENVIA CREDENCIALS")) {
            handleAcces();
            //accio sobre el panell sortida
        } else if (event.getActionCommand().equals("SORTIR")) {
            view.getPanelSortida().dialogSortida(this, comanda);
            //accio sobre el panell carta
        } else {
            //pagina anterior
            if (event.getActionCommand().equals("SEGUENT")) {

                int p = view.getCartaPanel().getPaginaCarta();
                if (p * 6 < view.getCartaPanel().getPag().getPlats().size()) {
                    view.getCartaPanel().paginaCarta(carta.getPlats(), p + 1);
                }
                view.getCartaPanel().getPag().repaint();
                view.getCartaPanel().getPag().revalidate();
                view.actualitzaVistaCarta();

                //Pagina seguent
            } else if (event.getActionCommand().equals("ANTERIOR")) {
                int p = view.getCartaPanel().getPaginaCarta();
                if (p > 1) {
                    view.getCartaPanel().paginaCarta(carta.getPlats(), p - 1);
                }
                view.actualitzaVistaCarta();

                //sobre la carta
            } else {
                handleAfegeixPlat(event);
            }
        }
    }

    /**
     * Controla el JDialog. Aquest ens diu la quantitat de plats a afegir a la comanda Actual
     *
     * @param plat  el plat que volem afegir
     * @param event l'esdeveniment
     */

    private void handleDialogPlat(Plat plat, ActionEvent event) {
        boolean fet = false;
        while (!fet) {
            DialogPlat dp = new DialogPlat(plat);
            if (dp.comprova()) {
                int unitats = dp.returninfo();
                for (int i = 0; i < unitats; i++) {
                    comandaActual.addPlat(plat);
                }

                fet = true;
            } else {
                fet = true;
                JOptionPane.showMessageDialog(view, "Només pots escriure nombres més grans que 0");
            }
        }

    }

    /**
     * Pepara la comanda actual abans de enviar-la al server
     */
    private void preparaComanda() {
        comandaActual.setUsuari(comanda.getUsuari());
        comandaActual.setHora(comanda.getHora());
        comandaActual.setData(comanda.getData());
    }

    /**
     * Missatge que anuncia que la comanda s'ha pogut enviar amb exit
     */
    public void missatgeExitComanda() {
        JOptionPane.showMessageDialog(viewComanda, "Comanda realitzada amb exit!");
    }

    /**
     * Missatge que auncia que la comanda no s'ha pogut realitzar amb exit
     *
     * @param error String que indica el tipus d'error
     */
    public void missatgeErrorComanda(String error) {
        JOptionPane.showMessageDialog(viewComanda, "Error a la comanda! " + error);
    }

    public void missatgeError(String error) {
        JOptionPane.showMessageDialog(viewComanda, error);
    }

    /**
     * Indica que el client vol sortir del programa.
     * Envia una comanda buida
     */
    public void enviaPagat() {
        serverConnect.enviaComanda(new Comanda());
    }


    /**
     * Getter de la carta
     *
     * @return la carta del restaurant
     */
    public Carta getCarta() {
        return carta;
    }

    /**
     * Setter de la carta del restaurant
     *
     * @param carta la nova carta
     */
    public void setCarta(Carta carta) {
        this.carta.setCarta(carta);
    }

    /**
     * Getter de la comanda
     *
     * @return la comanda del usuari
     */
    public Comanda getComanda() {
        return comanda;
    }

    public ControllerViewComanda getControllerViewComanda() {
        return controllerViewComanda;
    }

    /**
     * Setter de la comanda
     *
     * @param comanda
     */
    public void setComanda(Comanda comanda) {
        this.comanda = comanda;
    }

    /**
     * Setter dels panells de la comanda
     * S'encarrega d'activar els panells de la finestra principal que utilitzen la comanda
     *
     * @param comanda    La comanda dels comensals
     * @param controller Per tal de poder registrar el controller als diferents panells
     * @param carta      La carta del restaurant
     */
    public void setPanellsComanda(Comanda comanda, ControllerMainWindow controller, Carta carta) {
        view.activaPanellsComanda(comanda, controller, carta);

    }

    /**
     * Setter dels panells de la carta
     * S'encarrega d'activar els panells de la finestra principal que fan servir la carta
     *
     * @param carta      La carta del restaurant
     * @param controller Controlador per tal de registrar-lo.
     */
    public void setPanellsCarta(Carta carta, ControllerMainWindow controller) {
        view.actualitzaPlatsVistaCarta(carta, this);
        view.getCartaPanel().paginaCarta(carta.getPlats(), view.getCartaPanel().getPaginaCarta());
    }


    /**
     * Getter de la comanda actual, la que encara no s'ha enviat al server
     *
     * @return Comanda Actual
     */
    public Comanda getComandaActual() {
        return comandaActual;
    }


    /**
     * Comanda ja ha estat enviada. Crea una nova comanda buida
     */
    public void setComandaActual() {
        this.comandaActual = new Comanda();
    }


    /**
     * Getter per saber en quin panell del CardLayout ens trobem
     *
     * @return String indicant quin card
     */
    public String getCard() {
        return card;
    }

}
