package Controller;


import NetworkManager.ServerConnect;
import View.Vista;

import javax.swing.*;
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

        String ObjButtons[] = {"Si","No"};
        int PromptResult = JOptionPane.showOptionDialog(null,
                "Al tancar pagaràs automàticament totes les comandes.\nEstas segur que vols sortir? ", "Sortir",
                JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null,
                ObjButtons,ObjButtons[1]);
        if(PromptResult==0)
        {
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