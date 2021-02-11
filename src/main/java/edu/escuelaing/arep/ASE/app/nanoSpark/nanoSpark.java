package edu.escuelaing.arep.ASE.app.nanoSpark;

import edu.escuelaing.arep.ASE.app.httpServer.HttpServer;

import java.io.IOException;

import static edu.escuelaing.arep.ASE.app.nanoSpark.nanoSparkDemo.*;

public class nanoSpark {

    public static void main(String[] args) throws IOException {
        nanoSparkDemo spark = new nanoSparkDemo();
        HttpServer server = new HttpServer();
        server.startServer();
    }
}
