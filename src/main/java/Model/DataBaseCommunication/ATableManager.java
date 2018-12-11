package Model.DataBaseCommunication;

import Model.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class ATableManager {
    private static String url = "jdbc:sqlite:resources/DataBase.db"; // Set database project
    protected static Connection conn;
    //protected static Object lock = new Object();

    public ATableManager(){
        //url = "jdbc:sqlite:resources/DataBase.db"; // Set database project
        //connect();
        //closeConnection();
    }

    //Connect to database
    protected void connect() {
        conn = null;
        try {
            // create a connection to the database
            conn = DriverManager.getConnection(url);

            //System.out.println("Connection to SQLite has been established.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //Disconnect from database
    protected void closeConnection(){
        try {
            if (conn != null) {
                conn.close();
                //System.out.println("Connection to SQLite has been closed");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
