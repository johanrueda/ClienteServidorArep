package edu.escuelaing.arep.ASE.app.nanoSpark;

import java.util.HashMap;
import java.util.Map;

public class nanoSparkDemo {
    private static Map<String,String> path = new HashMap<>();

    public static String get(String Rpath){
        if (path.containsKey(Rpath)){
            return path.get(Rpath);
        }
        return null;
    }

}
