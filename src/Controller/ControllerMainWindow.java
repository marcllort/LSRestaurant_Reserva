package Controller;


import Model.Carta;
import Model.Comanda;
import Model.Plat;
import Model.Usuari;
import NetworkManager.ServerConnect;
import View.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ControllerMainWindow implements ActionListener {
    //VISTA
    private Vista view;
    private VistaEditorComanda viewComanda;
    //MODEL
    private Carta carta;
    private Comanda comanda;
    private Comanda comandaActual;
    private ServerConnect serverConnect;
    //CONTROLADOR EDITOR COMANDA
    private ControllerViewComanda controllerViewComanda;

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
            //Si es tracta del panell d'acces
            if (event.getActionCommand().equals("ENVIA CREDENCIALS")) {
                handleAcces();
                //Si es tracta d'una accio del panell sortida
            } else if (event.getActionCommand().equals("SORTIR")) {
                view.getPanelSortida().dialogSortida(this);

            } else if (event.getActionCommand().equals("PAGINA ANTERIOR")) {
                view.getPanelCarta().setQuinaPagina(view.getPanelCarta().getQuinaPagina() - 1, "anterior");
            } else if (event.getActionCommand().equals("PAGINA SEGUENT")) {
                view.getPanelCarta().setQuinaPagina(view.getPanelCarta().getQuinaPagina() + 1, "seguent");
            } else if (event.getSource() instanceof PanelPlats) {
                if (event.getActionCommand().equals("PLAT1-" + view.getPanelCarta().getQuinaPagina())) {
                    handleAfageixPlat(0, view.getPanelCarta().getQuinaPagina() - 1);
                    //afegim el plat a la comanda + mostrar Jdialog
                } else if (event.getActionCommand().equals("PLAT2-" + view.getPanelCarta().getQuinaPagina())) {
                    //afegim el plat a la comanda + mostrar Jdialog
                } else if (event.getActionCommand().equals("PLAT3-" + view.getPanelCarta().getQuinaPagina())) {
                    //afegim el plat a la comanda + mostrar Jdialog
                } else if (event.getActionCommand().equals("PLAT4-" + view.getPanelCarta().getQuinaPagina())) {
                    //afegim el plat a la comanda + mostrar Jdialog
                } else if (event.getActionCommand().equals("PLAT5-" + view.getPanelCarta().getQuinaPagina())) {
                    //afegim el plat a la comanda + mostrar Jdialog
                } else if (event.getActionCommand().equals("PLAT6-" + view.getPanelCarta().getQuinaPagina())) {
                    //afegim el plat a la comanda + mostrar Jdialog
                }
            }
        }
        //Si es tracta d'una accio sobre el JDialog de sortida
        if (event.getActionCommand().equals("SORTIR PROGRAMA")) {
            //Vista torna a ser la del principi
            //view.getPanelSortida().desactivaDialogSortida();
            handleSortida();

        } else if (event.getActionCommand().equals("QUEDAR-SE")) {
            view.changePanel("BUIT");
            //view.getPanelSortida().desactivaDialogSortida();
        }
    }               

    private void handleAcces() {

        String usuari = view.getTypedUsuari();
        String contrasenya = view.getTypedContrasenya();
        //Comprovem dades




        serverConnect.enviaUser(new Usuari(usuari, contrasenya));
        String userConfirmation = serverConnect.repUserConfirmation();
        if (userConfirmation.equals("true")) {

            //1.mostrem dialog de benvingut
            JOptionPane.showMessageDialog(view, "Benvingut!");

            view.changePanel("BUIT");

            serverConnect.startServerConnection(this);
            System.out.println("perfesto");
            /*while (true) {
                System.out.println("recibe objeto");
                Object objeto = serverConnect.repCartaComanda();

                if (objeto instanceof Comanda) {
                    comanda = (Comanda) serverConnect.repCartaComanda();
                    view.activaPanellsComanda(comanda, this);

                } else if (objeto instanceof Carta) {
                    System.out.println("recibido carta" + ((Carta) objeto).getPlat(1));
                    carta.setCarta((Carta) objeto);
                    view.activaPanellsCarta(carta, this);
                }
            }*/

        } else {
            JOptionPane.showMessageDialog(view, "Credencials incorrectes!");
            view.cleanFields();
        }
    }

    public void handleSortida() {
        view.cleanFields();
        this.view = new Vista();
        view.setVisible(true);
        view.registerController(this);
    }

    private void handleVistaComanda() {
        
        viewComanda = new VistaEditorComanda(comandaActual);
        viewComanda.setVisible(true);
        controllerViewComanda = new ControllerViewComanda(serverConnect, viewComanda, comandaActual);
        viewComanda.registerController(controllerViewComanda);
        
        while(controllerViewComanda.getIfFinestraActiva()){
            if( controllerViewComanda.getIfComandaEnviada()){
                comandaActual = new Comanda();
                view.actualitzaPanelEstatComanda(comanda);
                
            }
        }

    }

    private void handleAfageixPlat(int numPlat, int numPagina) {
        Plat p = view.getPanelCarta().getPaginaPlats(numPagina).getPlat(numPlat);

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
    
    private void handleMenu(ActionEvent event){

        if (event.getActionCommand().equals("ACCES CARTA")) {
            view.changePanel("CARTA");
            //comandaActual = new ArrayList<Plat>();
        } else if (event.getActionCommand().equals("ACCES ESTAT COMANDA")) {
            view.changePanel("ESTAT COMANDA");
        } else if (event.getActionCommand().equals("ACCES SORTIDA")) {
            view.changePanel("SORTIR");
        } else if (event.getActionCommand().equals("ACCES EDITOR COMANDA")) {
            handleVistaComanda();
        }
        
    }



    //1.cuando se envie comanda actualizar hora
    //2.Dejarlo todo como una sola comanda
    //3.cuando envio comanda borrar comanda actual
    //4.mirar platos que fallan
}