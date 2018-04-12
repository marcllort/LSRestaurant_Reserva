package Model;



//El model Rep la comanda i la carta del network, i se la pasa a la vista

//import Controller.Controller;
import Controller.Controller;
import View.Vista;

public class Main{
    public static void main(String[] args){

        Vista view;
        view = new Vista();
        Controller controller = new Controller(view);
        view.registerController(controller);


        view.setVisible(true);


    }


}
