package edu.escuelaing.arep.ASE.app.httpServer;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Johan
 * Clase principal de respuesta de las peticiones tipo URI
 */

public class Request {
    private String metodo;
    private String requestURI;
    private String HTTPVersion;
    private URI theuri;
    private Map<String,String> query;

    /**
     * clase hace realiza solicitudes de tipo URI
     * @param inputLine input
     */
    public Request(String inputLine) {
        try{
            String[] componente = inputLine.split(" ");
            metodo = componente[0];
            this.requestURI = componente[1];
            HTTPVersion = componente[2];
            setTheuri(new URI(requestURI));
            query = parseQuery(theuri.getQuery());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    /**
     * Clase que mapea la peticion URI
     * @param query query
     * @return Mapeo
     */
    private Map<String, String> parseQuery(String query) {
        if( query == null) return null;
        Map<String, String> theQuery = new HashMap();
        String[] nameValuePairs = query.split("&");
        for(String nameValuePair: nameValuePairs){
            int index = nameValuePair.indexOf("=");
            if(index!=-1){
                theQuery.put(nameValuePair.substring(0, index), nameValuePair.substring(index+1));
            }
        }
        return theQuery;
    }

    /**
     * Retorna URI
     * @return URI
     */
    public String getRequestURI() {
        return this.requestURI;
    }

    /**
     * metodo que convierte a string
     * @return URI
     */
    public String toString(){
        return metodo + " " + requestURI + " " + HTTPVersion + "\n\r" + getTheuri() + "\n\r" + "Query: " + query;
    }

    /**
     * obtiene el uri
     * @return URI
     */
    private URI getTheuri() {
        return theuri;
    }

    /**
     * Cambia el uri
     * @param theuri cambio  URI
     */
    public void setTheuri(URI theuri) {
        this.theuri = theuri;
    }
    
}
