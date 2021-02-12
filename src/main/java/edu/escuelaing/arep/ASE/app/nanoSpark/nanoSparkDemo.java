package edu.escuelaing.arep.ASE.app.nanoSpark;

import java.util.HashMap;
import java.util.Map;

/**
 * clase que realiza el ruta del framework spark
 */
public class nanoSparkDemo {
    private static Map<String,String> path = new HashMap<>();

    /**
     * Metodo que obtiene la ruta
     * @param Rpath path
     * @return string
     */
    public static String get(String Rpath){
        if (path.containsKey(Rpath)){
            return path.get(Rpath);
        }
        return null;
    }

}
