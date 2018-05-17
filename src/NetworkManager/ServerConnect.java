package NetworkManager;

import Controller.ControllerMainWindow;
import Model.Carta;
import Model.Comanda;
import Model.Json.ConfiguracioClient;
import Model.Json.LectorJson;
import Model.Plat;
import Model.Usuari;
import View.Vista;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

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
    private Vista vista;

    /**
     * Contructor amb parametres
     *
     * @param vista finestra principal
     */
    public ServerConnect(Vista vista) {

        try {
            lectorJSON = new LectorJson();
            ConfiguracioClient conf = lectorJSON.llegeixConfiguracioClient();
            portReserva = conf.lectorPortServer();
            ipReserva = conf.lectorIpServer();
            socket = new Socket(ipReserva, portReserva);
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
            dis = new DataInputStream(socket.getInputStream());
            this.vista = vista;
        } catch (IOException e) {
            vista.newDialog("No s'ha pogut connectar amb el servidor!");
            System.exit(0);
        }
    }

    /**
     * Relacio entre el controller i el server
     *
     * @param controller
     */
    public void startServerConnection(ControllerMainWindow controller) {
        this.controller = controller;
        start();                                        //Connectem al servidor
    }

    /**
     * Envia l'usuari al servidor
     *
     * @param user
     */
    public void enviaUser(Usuari user) {
        try {
            oos.writeObject(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Ens diu si l'usuari existeix, credencials correctes
     *
     * @return true si existeix, false si no
     */
    public String repUserConfirmation() {            //Si user correcte, retorna true, si incorrecte retorna missatge error i desconnecta
        try {
            return dis.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
            return "IOException";
        }
    }

    /**
     * Envia la carta
     *
     * @return
     */
    public Object repCartaComanda() {                             //Si user correcte envia la carta despres de enviar el true

        try {
            return ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;                                            //retona null en cas d'execepcio
        }

    }

    /**
     * Envia la comanda al server
     *
     * @param comanda
     */
    public void enviaComanda(Comanda comanda) {
        try {
            Time hora = new Time(System.currentTimeMillis());
            comanda.setHora(hora);
            oos.writeObject(comanda);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Ens confirma si s'ha pogut demanar tots els plats
     *
     * @return
     */
    public String repComandaConfirmation() {             //tornen true si ha anat i sino string amb  plats que falten
        try {
            return dis.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
            return "IOException";
        }
    }

    /**
     * Desconnecta el server
     */
    public void serverDisconnect() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Getter de la resposta
     *
     * @return
     */
    public String getResposta() {
        return resposta;
    }


    @Override
    public void run() {
        Carta carta = new Carta();
        while (true) {
            Object objeto = repCartaComanda();

            if (objeto instanceof String) {
                resposta = (String) objeto;
                if (resposta.equals("true")) {
                    controller.missatgeExitComanda();
                } else {
                    controller.missatgeErrorComanda(resposta);
                }
            }
            if (objeto instanceof Comanda) {
                Comanda comanda = (Comanda) objeto;
                controller.setComanda(comanda);
                controller.setPanellsComanda(comanda, controller, carta);
                vista.actualitzaPanelEstatComanda(comanda);
                vista.revalidate();

                try {
                    if (controller.getCard().equals("ESTAT COMANDA")) {
                        vista.changePanel("ESTAT COMANDA");
                    }
                } catch (NullPointerException ne) {

                }
            }
            if (objeto instanceof Carta) {
                carta = (Carta) objeto;
                controller.setCarta(carta);
                controller.setPanellsCarta(carta, controller);
            }
        }
    }


}
