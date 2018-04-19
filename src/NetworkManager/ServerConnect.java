package NetworkManager;

import Model.Comanda;
import Model.Json.ConfiguracioClient;
import Model.Json.LectorJson;
import Model.Plat;
import Model.Usuari;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.sql.Time;
import java.util.ArrayList;

public class ServerConnect {

    public static int portReserva;
    private static String ipReserva = "localhost";
    private Socket socket;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private DataInputStream dis;
    private String resposta;
    private LectorJson lectorJSON;


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
        } catch (IOException e) {
            e.printStackTrace();
            return null;                                            //retona null en cas d'execepcio
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
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


}
