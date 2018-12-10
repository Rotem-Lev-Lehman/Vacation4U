package Model.DataBaseCommunication;

import Model.*;

import java.sql.SQLException;
import java.util.List;

//Database manager interface
public interface IDataBaseManager {
    void CreateUser(User user) throws SQLException; //Create User
    User ReadUser(String username); //Read user
    List<User> ReadSimilarUsers(String username); //Read user with similar usernames
    void UpdateUser(String username, User user); //Update user information
    void DeleteUser(User user); //Delete User


}
