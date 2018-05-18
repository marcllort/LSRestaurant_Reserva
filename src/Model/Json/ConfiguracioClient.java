package Model.Json;

public class ConfiguracioClient {

    private int portServer;            //  Port de connexio amb el servidor

    private String ipServer;        // IP del servidor


    /**
     * Retorna el port de connexio amb el servidor
     *
     * @return port
     */
    public int lectorPortServer() {
        return portServer;
    }

    /**
     * Retorna la IP del servidor
     *
     * @return ip
     */
    public String lectorIpServer() {
        return ipServer;
    }

}

