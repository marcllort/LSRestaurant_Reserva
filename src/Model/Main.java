package Model;

//El model Rep la comanda i la carta del network, i se la pasa a la vista

//import ControllerMainWindow.ControllerMainWindow;

import Controller.*;
import NetworkManager.ServerConnect;
import View.Vista;
import View.VistaEditorComanda;

public class Main {
    public static void main(String[] args) {

        Vista view;
        view = new Vista();
        view.setVisible(true);
        ServerConnect serverConnect = new ServerConnect(view);
        ControllerMainWindow controller = new ControllerMainWindow(view, serverConnect);
        ControllerWindow  controllerWindow = new ControllerWindow(serverConnect, controller, view);
        view.registerController(controller, controllerWindow);
        //serverConnect.startServerConnection(controller);

    }

}
