package edu.escuelaing.arep.ASE.app.httpServer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HttpServer {
    private static boolean running;
    private dataBase connect= null;
    private Map<String,String> request;
    private int puerto = 36000;

    public HttpServer() {
        this.puerto = getPort();
        request = new HashMap<>();
    }

    public HttpServer(int port) {
        this.puerto = port;
        request = new HashMap<>();
    }



    public  void startServer() {
            try {
                ServerSocket serverSocket = null;
                this.puerto = getPort();

                try {
                    serverSocket = new ServerSocket(puerto);
                } catch (IOException e) {
                    System.err.println("Could not listen on port: " + getPort());
                    System.exit(1);
                }

                running = true;
                while (running) {
                    try {
                        Socket clientSocket = null;
                        try {
                            System.out.println("Listo para recibir en puerto 36000 ...");
                            clientSocket = serverSocket.accept();
                        } catch (IOException e) {
                            System.err.println("Accept failed.");
                            System.exit(1);
                        }
                        processRequest(clientSocket);
                        clientSocket.close();
                    } catch (IOException ex) {
                        Logger.getLogger(HttpServer.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                serverSocket.close();
            } catch (IOException ex) {
                Logger.getLogger(HttpServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    private void processRequest(Socket clientSocket) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String inputLine;
        boolean lineReady = true;
        Request req = null;
        while ((inputLine = in.readLine()) != null) {
            if(lineReady){
                req = new Request(inputLine);
                lineReady = false;
            }
            if (!in.ready()) {
                break;
            }
        }
        if (req!=null){
            createResponse(req, new PrintWriter(clientSocket.getOutputStream(), true), clientSocket);
        }
        in.close();
    }

    private void createResponse(Request req, PrintWriter printWriter, Socket clientSocket) throws IOException {
        if(req.getRequestURI().startsWith("/dataBase")) {
            String db = "HTTP/1.1 200 OK\r\n"
                    + "Content-Type: text/html\r\n"
                    + "\r\n"
                    + "<!DOCTYPE html>\n"
                    + "<html>\n"
                    + "<head>\n"
                    + "<meta charset=\"UTF-8\">\n"
                    + "<title>Title of the document</title>\n"
                    + "</head>\n"
                    + "<body>\n"
                    + "<h1>Mi propio mensaje</h1>\n"
                    + "</body>\n"
                    + "</html>\n";
            db += getDataBase();
            printWriter.println(db);
        }else{
            getStaticResource(req.getRequestURI(),printWriter,clientSocket);
        }
        printWriter.close();
    }

    private void getStaticResource(String requestURI, PrintWriter printWriter, Socket clientSocket) throws IOException {
        Path file = Paths.get("src/main/resources/" + requestURI);
        String resource = "HTTP/1.1 200 OK\r\n";
        if (requestURI.contains(".html") || requestURI.equals("/")){
            requestURI = "index.html";
            resource += ("Content-Type: text/html\r\n"
                    + "\r\n"
                    + "<!DOCTYPE html>\n");
        }else if (requestURI.contains(".jpg")) {
            getImage(requestURI, clientSocket.getOutputStream());
        }
        try (InputStream in = Files.newInputStream(file);
             BufferedReader reader = new BufferedReader(new InputStreamReader(in)))
        {
            printWriter.println(resource);
            String line = null;
            while ((line = reader.readLine()) != null) {
                printWriter.println(line);
            }
        } catch (IOException ex) {
            Logger.getLogger(HttpServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void getImage(String requestURI, OutputStream outputStream) {
        File file = new File("src/main/resources" + requestURI);
        try {
            BufferedImage pic = ImageIO.read(file);
            ByteArrayOutputStream picShow = new ByteArrayOutputStream();
            DataOutputStream picDraw = new DataOutputStream(outputStream);
            ImageIO.write (pic, "JPG", picShow);
            picDraw.writeBytes("HTTP/1.1 200 OK\r\n" + "Content-Type: image/jpg \r\n\r\n");
            picDraw.write(picShow.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private String getDataBase() {
        dataBase db = new dataBase();
        ArrayList<String []> data = db.getTable();
        String list = "";
        for (String [] datos : data) {
            list += datos [0] + ". Nombre : " + datos [1] + "\n";
        }
        return list;
    }

    private int getPort() {
        if(System.getenv("PORT") != null){
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 36000;
    }
    }