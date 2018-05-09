package Controller;


import Model.Carta;
import Model.Comanda;
import Model.Plat;
import Model.Usuari;
import NetworkManager.ServerConnect;
import View.DialogPlat;
import View.PlatsPanel;
import View.Vista;
import View.VistaEditorComanda;
import View.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


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
    private boolean obrirEditorComanda;

    /**
     * Constructor amb parametres
     *
     * @param view relacio vista amb controlador
     */


    public ControllerMainWindow(Vista view, ServerConnect serverConnect) {
        this.view = view;
        this.serverConnect = serverConnect;
        carta = new Carta();

        comandaActual = new Comanda();

        obrirEditorComanda = false;
    }


    /**
     * Gestiona els esdeveniments
     *
     * @event Font de l'esdeveniment
     */

    public void actionPerformed(ActionEvent event) {
        //Si es tracta d'una accio sobre el menu
        view.creaMenu(this);
        if (event.getSource() instanceof JMenuItem) {
            handleMenu(event);
        } else {
            //Si es tracta d'alguna opció sobre els panells
            handlePanels(event);
        }
        //Si es tracta d'una accio sobre el JDialog de sortida
        if (event.getActionCommand().equals("SORTIR PROGRAMA")) {
            //Vista torna a ser la del principi
            //view.getPanelSortida().desactivaDialogSortida();
            handleSortida();

        } else if (event.getActionCommand().equals("QUEDAR-SE")) {
            view.changePanel("BUIT");
            view.creaMenu(this);
            //view.getPanelSortida().desactivaDialogSortida();
        }
    }

    /**
     * Comprova que les credencials enviades per l'usuari siguin correctes
     */
    private void handleAcces() {

        String usuari = view.getTypedUsuari();
        String contrasenya = view.getTypedContrasenya();
        if (usuari.equals("") || contrasenya.equals("")){
            JOptionPane.showMessageDialog(view, "Has d'omplir els espais d'usuari i contrasenya");
            view.cleanFields();
        }else{
            serverConnect.enviaUser(new Usuari(usuari, contrasenya));
            String userConfirmation = serverConnect.repUserConfirmation();
            if (userConfirmation.equals("true")) {
                JOptionPane.showMessageDialog(view, "Benvingut!");
                view.changePanel("BUIT");
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
        //view.registerController(this);
    }

    /**
     * S'encarrega d'obrir la nova ventana per poder editar la comanda
     * @param viewComanda la vista de la comanda
     */
    private void handleVistaComanda( VistaEditorComanda viewComanda) {

        if(comandaActual.getPlats().size() == 0){
            viewComanda.setVisible(false);
            JOptionPane.showMessageDialog(view, "Afageix plats per tal d'editar la teva comanda");
            view.creaMenu(this);
            view.activaPanellsCarta(carta, this);
            view.activaPanellsComanda(comanda, this, carta);
        }else{

            viewComanda = new VistaEditorComanda(comandaActual);
            viewComanda.setVisible(true);
            preparaComanda();
            controllerViewComanda = new ControllerViewComanda(serverConnect, comandaActual, viewComanda, this);
            viewComanda.registerController(controllerViewComanda);

               /* if( controllerViewComanda.getIfComandaEnviada()){
                    System.out.println(controllerViewComanda.getIfComandaEnviada() + "esta enviada o no");
                    viewComanda.setVisible(false);
                    comandaActual = new Comanda();
                    view.creaMenu(this);
                    view.actualitzaPanelEstatComanda(comanda);
                    view.activaPanellsCarta(carta, this);
                    view.activaPanellsComanda(comanda, this, carta);


                }*/

            view.creaMenu(this);
            view.actualitzaPanelEstatComanda(comanda);

            view.activaPanellsCarta(carta, this);

            view.activaPanellsComanda(comanda, this, carta);

        }

    }

    /**
     * S'encarrega de controlar quan s'afegeix un plat
     * @param event font de l'esdeveniment
     */

    private void handleAfegeixPlat(ActionEvent event) {

        int i = view.getCartaPanel().getPaginaActual();
        for(int j = 0; j < view.getCartaPanel().getPagina(i-1).getPlats().size(); j++){
            if(event.getActionCommand().equals((j+1) + "-" + i)){
                handleDialogPlat(view.getCartaPanel().getPagina(i-1).getPlats().get(j), event);
                break;
            }
        }

        for (int x = 0; x < comandaActual.getPlats().size(); x++){
            System.out.println(comandaActual.getPlat(x).getNomPlat());
        }


    }

    public Carta getCarta() {
        return carta;
    }

    public void setCarta(Carta carta) {
        this.carta.setCarta(carta);
    }

    public Comanda getComanda() {
        return comanda;
    }

    public void setComanda(Comanda comanda) {
        this.comanda = comanda;
    }

    public void setPanellsComanda(Comanda comanda, ControllerMainWindow controller, Carta carta) {
        view.activaPanellsComanda(comanda, controller, carta);
        view.changePanel("BUIT");
    }

    public void setPanellsCarta(Carta carta, ControllerMainWindow controller) {
        view.activaPanellsCarta(carta, controller);

    }

    /**
     * Gestiona els esdeveniments sobre el menu
     * @param event Font de l'esdeveniment
     */

    private void handleMenu(ActionEvent event){

        if (event.getActionCommand().equals("ACCES CARTA")) {
            view.changePanel("CARTA");
            //comandaActual = new ArrayList<Plat>();
        } else if (event.getActionCommand().equals("ACCES ESTAT COMANDA")) {
            view.changePanel("ESTAT COMANDA");
        } else if (event.getActionCommand().equals("ACCES SORTIDA")) {
            view.changePanel("SORTIR");
        } else if (event.getActionCommand().equals("ACCES EDITOR COMANDA")) {
            viewComanda = new VistaEditorComanda(comandaActual);
            handleVistaComanda(viewComanda);
        }

    }

    public boolean getIfObrirFinestra(){return  obrirEditorComanda;}

    public Comanda getComandaActual() {
        return comandaActual;
    }

    /**
     * Esdeveniments sobre els panells
     * @param event font de l'esdeveniment
     */

    private void handlePanels(ActionEvent event){

            //accio sobre el panell d'acces
        if (event.getActionCommand().equals("ENVIA CREDENCIALS")) {
            handleAcces();

            //accio sobre el panell sortida
        } else if (event.getActionCommand().equals("SORTIR")) {
            view.getPanelSortida().dialogSortida(this);

            //accio sobre el panell carta
        } else  {

            //pagina anterior
            if (event.getActionCommand().equals("ANTERIOR")) {
                view.getCartaPanel().cambiaPagina("anterior");
                view.creaMenu(this);
                view.getCartaPanel().registerController(this);
                view.getPanelSortida().registerController(this);

                //Pagina seguent
            } else if (event.getActionCommand().equals("SEGUENT")) {
                view.getCartaPanel().cambiaPagina("seguent");
                view.creaMenu(this);
                view.getCartaPanel().registerController(this);
                view.getPanelSortida().registerController(this);

                //sobre la carta
            }else {
                System.out.println("porfin");
                handleAfegeixPlat(event);
            }
        }
    }

    /**
     * Controla el JDialog. Aquest ens diu la quantitat de plats a afegir a la comanda Actua
     * @param plat el plat que volem afegir
     * @param event l'esdeveniment
     */

    private void handleDialogPlat(Plat plat, ActionEvent event){
        boolean fet = false;
        while (!fet){
            DialogPlat dp = new DialogPlat(plat);
            if(dp.comprova()){
                int unitats = dp.returninfo();
                for (int i = 0; i < unitats; i++){
                    comandaActual.addPlat(plat);
                }

                fet = true;
            }else{
                JOptionPane.showMessageDialog(view, "Nomes pots escriure numeros mes grans que 0");
            }
        }
        view.creaMenu(this);
        view.activaPanellsCarta(carta, this);
        view.activaPanellsComanda(comanda, this, carta);

    }


    private void preparaComanda(){
        comandaActual.setUsuari(comanda.getUsuari());
        comandaActual.setHora(comanda.getHora());
        comandaActual.setData(comanda.getData());
    }

    public void missatgeExitComanda(){
        JOptionPane.showMessageDialog(viewComanda, "Comanda realitzada amb exit!");
    }

    public void missatgeErrorComanda(String error){
        JOptionPane.showMessageDialog(viewComanda, "Error a la comanda! " + error);
    }

    public void setComandaActual(Comanda comandaActual){
        this.comandaActual = new Comanda();
        System.out.println("comanda actualitzada");

    }

}
