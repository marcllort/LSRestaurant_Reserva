package Controller;


import Model.Comanda;
import NetworkManager.ServerConnect;
import View.Vista;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class ControllerWindow implements WindowListener {
    private ServerConnect serverConnect;
    private ControllerMainWindow controller;
    private Vista view;

    /**
     * Constructor amb parametres
     * @param networkconnect connexio amb el server
     * @param controller controller de la finestra principal
     * @param view la vista principal
     */
    public ControllerWindow(ServerConnect networkconnect, ControllerMainWindow controller, Vista view) {
        this.serverConnect = networkconnect;
        this.controller = controller;
        this.view = view;
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        System.out.println("tancant");
        String ObjButtons[] = {"Si","No"};
        int PromptResult = JOptionPane.showOptionDialog(null,
                "Al tancar pagaràs automàticament totes les comandes.\nEstas segur que vols sortir? ", "Sortir",
                JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null,
                ObjButtons,ObjButtons[1]);
        if(PromptResult==0)
        {
            serverConnect.enviaComanda(new Comanda());
            System.exit(0);
        }

    }                           //Desconnectem del servidor quan tanquem la finestra del client

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}