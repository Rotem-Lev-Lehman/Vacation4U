package Model.DataBaseCommunication;

import Model.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsersTableManager extends ATableManager {
    //Create user
    public void Create(User user) throws SQLException {
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
        connect(); //Connect to database
        User user = ReadWithOutConnection(username);
        closeConnection();
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
            e.printStackTrace();
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
            e.printStackTrace();
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
            e.printStackTrace();
        }
        closeConnection(); //Disconnect from database
    }

    protected User ReadWithOutConnection(String username){
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
            e.printStackTrace();
        }
        return user;
    }
}
