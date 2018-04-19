package Model;

//El model Rep la comanda i la carta del network, i se la pasa a la vista

//import Controller.Controller;

import Controller.Controller;
import NetworkManager.ServerConnect;
import View.Vista;

public class Main {
    public static void main(String[] args) {

        Vista view;
        view = new Vista();
        view.setVisible(true);
        ServerConnect serverConnect = new ServerConnect();
        Controller controller = new Controller(view, serverConnect);
        view.registerController(controller);
        //serverConnect.startServerConnection(controller);

    }

}
