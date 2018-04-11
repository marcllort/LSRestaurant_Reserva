package Model;



//El model Rep la comanda i la carta del network, i se la pasa a la vista

//import Controller.Controller;
import View.Vista;

public class Main{
    public static void main(String[] args){

        Plat plat = new Plat("pasta", 10);


        Carta carta = new Carta();
        carta.afageixPlat(plat);
        Comanda c = new Comanda();

        Vista view;
        view = new Vista(carta, c );

        view.setVisible(true);


    }
}
