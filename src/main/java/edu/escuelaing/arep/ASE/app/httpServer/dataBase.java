package edu.escuelaing.arep.ASE.app.httpServer;

import java.sql.*;
import java.util.ArrayList;

/**
 * @author Johan
 * Clase principal que hace la conexion a la base de datos
 */
public class dataBase {
    private static String url ="jdbc:postgresql://ec2-100-24-139-146.compute-1.amazonaws.com:5432/d3iic92vv476ra";
    private static String user = "jgdjyaphxrrrgv";
    private static String password = "a30be3f4ee4adb660f695bb9a1f91e23cb4e2f271d1b0fddc957f29c2d215729";
    private static Connection connection = null;

    /**
     * realiza la conexion de la base de datos en postgresql
     */
    public dataBase(){
        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("Antes de la conxecion");
            connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException c) {
            c.printStackTrace();
        } catch (SQLException s) {
            s.printStackTrace();
        }
    }

    /**
     * Obtiene los datos de la base de datos
     * @return lista de datos
     */
    public ArrayList<String[]> getTable() {
        ArrayList <String[]> data = new ArrayList<>();
        try {
            Statement state = connection.createStatement();
            ResultSet ans = state.executeQuery("SELECT * FROM usuarios");
            while (ans.next()){
                String id = ans.getString("id");
                String name = ans.getString("nombre");
                String [] inf = {id, name};
                data.add(inf);
            }
        } catch (SQLException s) {
            s.printStackTrace();
        }
        return data;
    }
}
