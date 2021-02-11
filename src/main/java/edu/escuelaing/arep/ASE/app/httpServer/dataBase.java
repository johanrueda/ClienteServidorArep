package edu.escuelaing.arep.ASE.app.httpServer;

import java.sql.*;
import java.util.ArrayList;

public class dataBase {
    private static String url ="jdbc:postgres://npnxrkzsftjibx:1bc234c47dcab7877e820f7f7610189eea358f254785cbdc9e29ec146fec096f@ec2-54-166-114-48.compute-1.amazonaws.com:5432/dbcoe2qnse2i3q";
    private static String user = "npnxrkzsftjibx";
    private static String password = "1bc234c47dcab7877e820f7f7610189eea358f254785cbdc9e29ec146fec096f";
    private static Connection connection = null;

    public dataBase(){
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException c) {
            c.printStackTrace();
        } catch (SQLException s) {
            s.printStackTrace();
        }
    }
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
