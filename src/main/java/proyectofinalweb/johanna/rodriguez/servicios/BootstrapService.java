package proyectofinalweb.johanna.rodriguez.servicios;

import org.h2.tools.Server;

import java.sql.SQLException;

public class BootstrapService {

    private static BootstrapService instancia;

    public static BootstrapService getInstancia() {
        if (instancia == null)
            instancia = new BootstrapService();

        return instancia;
    }

    /**
     * Iniciar la base de datos
     *
     * @throws SQLException
     */
    public static void startDB() throws SQLException {
        Server.createTcpServer("-tcpPort", "9092", "-tcpAllowOthers").start();
    }

    /**
     * Para detener la base de datos
     *
     * @throws SQLException
     */
    public static void stopDB() throws SQLException {
        Server.shutdownTcpServer("tcp://localhost:9092", "", true, true);
    }
}
