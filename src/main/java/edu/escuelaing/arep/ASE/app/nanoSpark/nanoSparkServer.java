package edu.escuelaing.arep.ASE.app.nanoSpark;

import edu.escuelaing.arep.ASE.app.httpServer.HttpServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class nanoSparkServer {
    private  static nanoSparkServer _theinstance = new nanoSparkServer();
    private HttpServer hserver = new HttpServer();

    public static nanoSparkServer getInstance() {
        return _theinstance;
    }
    private Map<String,String> bodyMap = new HashMap();

    private nanoSparkServer(){
        hserver.startServer();
    }

    public void get(String path, String body) {
        bodyMap.put(path,body);
    }

    public String getValue(String path){
        return bodyMap.get(path);
    }
}
