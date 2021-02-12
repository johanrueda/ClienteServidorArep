package edu.escuelaing.arep.ASE.app.urlReader;

// Ejercicio 1

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

 class urlScanner {
    /**
     * Metodo que inicia scaneo la URL
     * @param args args
     */
    public static void main (String [] args){
        scanURL ("https://murmuring-anchorage-59220.herokuapp.com:80/web/index.html");
    }

    /**
     * Metodo que scanea la URL
     * @param s url
     */
    private static void scanURL(String s) {
        try {
            URL siteURL = new URL (s);
            System.out.println ("URL: " + siteURL);
            System.out.println ("Protocol: " + siteURL.getProtocol());
            System.out.println ("Host: " + siteURL.getHost());
            System.out.println ("Port: " + siteURL.getPort());
            System.out.println ("Path: " + siteURL.getPath());
            System.out.println ("File: " + siteURL.getFile());
            System.out.println ("Query: " + siteURL.getQuery());
            System.out.println ("Ref.: " + siteURL.getRef());
            System.out.println ("Authority: " + siteURL.getAuthority());
            System.out.println("-------------");
        } catch (MalformedURLException ex) {
            Logger.getLogger(urlScanner.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
