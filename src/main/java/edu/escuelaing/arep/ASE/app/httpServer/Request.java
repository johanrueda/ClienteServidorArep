package edu.escuelaing.arep.ASE.app.httpServer;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class Request {
    private String metodo;
    private String requestURI;
    private String HTTPVersion;
    private URI theuri;
    private Map<String,String> query;

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

    public String getRequestURI() {
        return this.requestURI;
    }

    public String toString(){
        return metodo + " " + requestURI + " " + HTTPVersion + "\n\r" + getTheuri() + "\n\r" + "Query: " + query;
    }

    private URI getTheuri() {
        return theuri;
    }

    public void setTheuri(URI theuri) {
        this.theuri = theuri;
    }
    
}
