package Model;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseManager {

    private String url;
    private Connection conn;

    public DataBaseManager(){
        url = "jdbc:sqlite:resources/DataBase.db";
        connect();
        closeConnection();
    }
    private void connect() {
        conn = null;
        try {
            // create a connection to the database
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    private void closeConnection(){
        try {
            if (conn != null) {
                conn.close();
                System.out.println("Connection to SQLite has been closed");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
