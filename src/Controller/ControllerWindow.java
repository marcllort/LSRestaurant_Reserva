package Controller;


import NetworkManager.ServerConnect;
import View.Vista;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class ControllerWindow implements WindowListener {
    private ServerConnect networkconnect;
    private Controller controller;
    private Vista view;


    public ControllerWindow(ServerConnect networkconnect, Controller controller, Vista view) {
        this.networkconnect = networkconnect;
        this.controller = controller;
        this.view = view;
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        System.out.println("tancant");
        view.getPanelSortida().desactivaDialogSortida();
        controller.handleSortida();
        networkconnect.serverDisconnect();
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
