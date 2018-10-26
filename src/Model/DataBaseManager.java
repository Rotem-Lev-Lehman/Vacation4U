package Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBaseManager implements IDataBaseManager {

    private String url;
    private Connection conn;

    public DataBaseManager(){
        url = "jdbc:sqlite:resources/DataBase.db"; // Set databse project
        //connect();
        //closeConnection();
    }

    //Create user
    public void Create(User user) throws SQLException{
        connect(); //connect to database

        //create user - sql command
        String sql = "INSERT INTO users(username,password,birthdate,firstname,lastname,city) VALUES(?,?,?,?,?,?)";

        //try to create user
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
            closeConnection(); //close connection to datebase
            throw e;
        }
        closeConnection(); //close connection
    }

    //Read User
    public User Read(String username){
        connect(); //Connect to databse

        //Read user - sql commend
        String sql = "SELECT username, password, birthdate, firstname, lastname, city FROM users WHERE username = ?";
        User user = null;
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // set the value
            pstmt.setString(1, username);
            //
            ResultSet rs = pstmt.executeQuery();

            // loop through the result set
            while (rs.next()) {
                user = new User(rs.getString("username"),rs.getString("password"),rs.getString("birthdate"),rs.getString("firstname"),rs.getString("lastname"),rs.getString("city"));
                break;
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        closeConnection(); //disconnect from datebase
        return user;
    }

    //Read similar users (similar users - users with similar usernames)
    public List<User> ReadSimilar(String username){
        connect(); //connect to database

        //sql commend
        String sql = "SELECT username, password, birthdate, firstname, lastname, city FROM users WHERE username LIKE ?";

        List<User> users = new ArrayList<User>(); //list of similar users
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

        closeConnection(); //disconnect from database
        return users;
    }

    //Update user information
    public void Update(String username, User user) {
        connect(); //Connect to databse
        //SQL commend
        String sql = "UPDATE users SET username = ? , "
                + "password = ? , "
                + "birthdate = ? , "
                + "firstname = ? , "
                + "lastname = ? , "
                + "city = ? "
                + "WHERE username = ?";

        try {
            //Run sql commend
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
        closeConnection(); //disconnect from databse
    }

    //Delte user from database
    public void Delete(User user) {
        connect(); //Connect to database
        String sql = "DELETE FROM users WHERE username = ?"; //SQL commend

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql); //Run SQL commend
            // set the corresponding param
            pstmt.setString(1,user.getUsername());
            // execute the delete statement
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        closeConnection(); //Disconnect from database
    }

    //Connect to database
    private void connect() {
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
    private void closeConnection(){
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
