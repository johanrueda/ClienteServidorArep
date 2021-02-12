package edu.escuelaing.arep.ASE.app.nanoSpark;

import edu.escuelaing.arep.ASE.app.httpServer.HttpServer;



public class nanoSpark {

    public static void main(String[] args) {
        nanoSparkDemo spark = new nanoSparkDemo();
        HttpServer server = new HttpServer();
        server.startServer();
    }
}
