package Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBaseManager implements IDataBaseManager {

    private String url;
    private Connection conn;

    public DataBaseManager(){
        url = "jdbc:sqlite:resources/DataBase.db";
        //connect();
        //closeConnection();
    }
    public void Create(User user) throws SQLException{
        connect();
        String sql = "INSERT INTO users(username,password,birthdate,firstname,lastname,city) VALUES(?,?,?,?,?,?)";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getBirthdate());
            pstmt.setString(4, user.getFirstName());
            pstmt.setString(5, user.getLastName());
            pstmt.setString(6, user.getCity());
            pstmt.executeUpdate();
        }
        catch (SQLException e){
            closeConnection();
            throw e;
        }
        closeConnection();
    }

    public List<User> Read(String username){
        connect();
        String sql = "SELECT username, password, birthdate, firstname, lastname, city FROM users WHERE username LIKE ?";
        List<User> users = new ArrayList<User>();
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // set the value
            pstmt.setString(1, "%" + username + "%");
            //
            ResultSet rs = pstmt.executeQuery();

            // loop through the result set
            while (rs.next()) {
                users.add(new User(rs.getString("username"),rs.getString("password"),rs.getString("birthdate"),rs.getString("firstname"),rs.getString("lastname"),rs.getString("city")));
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        closeConnection();
        return users;
    }

    public void Update(String username, User user) {
        connect();
        String sql = "UPDATE users SET username = ? , "
                + "password = ? , "
                + "birthdate = ? , "
                + "firstname = ? , "
                + "lastname = ? , "
                + "city = ? "
                + "WHERE username = ?";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // set the corresponding param
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getBirthdate());
            pstmt.setString(4, user.getFirstName());
            pstmt.setString(5, user.getLastName());
            pstmt.setString(6, user.getCity());
            pstmt.setString(7, username);
            // update
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        closeConnection();
    }

    public void Delete(User user) {
        connect();
        String sql = "DELETE FROM users WHERE username = ?";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // set the corresponding param
            pstmt.setString(1,user.getUsername());
            // execute the delete statement
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
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
