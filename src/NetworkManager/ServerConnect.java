package NetworkManager;

import Controller.ControllerMainWindow;
import Model.Carta;
import Model.Comanda;
import Model.Json.ConfiguracioClient;
import Model.Json.LectorJson;
import Model.Plat;
import Model.Usuari;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Time;

public class ServerConnect extends Thread {

    public static int portReserva;
    private static String ipReserva = "localhost";
    private Socket socket;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private DataInputStream dis;
    private String resposta;
    private LectorJson lectorJSON;
    private ControllerMainWindow controller;


    public ServerConnect() {

        try {
            lectorJSON = new LectorJson();
            ConfiguracioClient conf = lectorJSON.llegeixConfiguracioClient();
            portReserva = conf.lectorPortServer();
            ipReserva = conf.lectorIpServer();
            socket = new Socket(ipReserva, portReserva);
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
            dis = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void startServerConnection(ControllerMainWindow controller) {
        this.controller = controller;
        start();                                        //Connectem al servidor
    }

    public void enviaUser(Usuari user) {
        try {
            oos.writeObject(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String repUserConfirmation() {            //Si user correcte, retorna true, si incorrecte retorna missatge error i desconnecta
        try {
            return dis.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
            return "IOException";
        }
    }

    public Object repCartaComanda() {                             //Si user correcte envia la carta despres de enviar el true

        try {
            return ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;                                            //retona null en cas d'execepcio
        }

    }

    public void enviaComanda(Comanda comanda) {
        try {
            Time hora = new Time(System.currentTimeMillis());
            comanda.setHora(hora);
            oos.writeObject(comanda);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String repComandaConfirmation() {             //tornen true si ha anat i sino string amb  plats que falten
        try {
            return dis.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
            return "IOException";
        }
    }

    public void serverDisconnect() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void run() {
        Carta carta = new Carta();
        while (true) {
            Object objeto = repCartaComanda();

            if (objeto instanceof Comanda) {
                Comanda comanda = (Comanda) objeto;
                System.out.println("data: "+comanda.getData());
                Plat p = new Plat("Pasta", 1);
                comanda.addPlat(p);
                controller.setComanda(comanda);
                controller.setPanellsComanda(comanda, controller, carta);


            } else if (objeto instanceof Carta) {
                System.out.println("recibido carta" + ((Carta) objeto).getPlat(1));
                carta = (Carta) objeto;
               /* ArrayList<Plat> plats = new ArrayList<Plat>();
                Plat p = new Plat("Pasta", 10);
                plats.add(p);
                Date d = new Date(12, 12, 12);
                Time t =  new Time(12, 12, 12);
                Comanda comanda = new Comanda(plats, d, t, "Paula");
                */
               controller.setCarta(carta);
                controller.setPanellsCarta(carta, controller);

            }
            //controlador.updateVista(messages);          //Quan llegim el que ens envia el server, acutlaitzem a la vista
        }
    }

}
