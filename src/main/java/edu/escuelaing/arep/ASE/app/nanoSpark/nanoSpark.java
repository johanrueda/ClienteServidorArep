package edu.escuelaing.arep.ASE.app.nanoSpark;

import edu.escuelaing.arep.ASE.app.httpServer.HttpServer;


/**
 * Clase principal que inicia el servicio de mini Spark
 */
public class nanoSpark {
    /**
     * Metodo prinicipal que inicia el el servidor
     * @param args principal
     */
    public static void main(String[] args) {
        nanoSparkDemo spark = new nanoSparkDemo();
        HttpServer server = new HttpServer();
        server.startServer();
    }
}
