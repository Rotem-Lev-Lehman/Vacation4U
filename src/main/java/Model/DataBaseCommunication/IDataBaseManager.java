package Model.DataBaseCommunication;

import Model.*;

import java.sql.SQLException;
import java.util.List;

//Database manager interface
public interface IDataBaseManager {
    void Create(User user) throws SQLException; //Create User
    User Read(String username); //Read user
    List<User> ReadSimilar(String username); //Read user with similar usernames
    void Update(String username, User user); //Update user information
    void Delete(User user); //Delete User
}
