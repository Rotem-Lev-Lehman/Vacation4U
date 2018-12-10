package Model;

import Model.DataBaseCommunication.IDataBaseManager;

import java.sql.SQLException;
import java.util.List;

//Abstract model
public abstract class AModel {
    protected IDataBaseManager dataBaseManager;

    //Create user
    public void Create(User user) throws SQLException {
        dataBaseManager.Create(user);
    }

    //Read similar users
    public List<User> ReadSimilar(String username) {
        return dataBaseManager.ReadSimilar(username);
    }

    //Read user
    public User Read(String username){
        return dataBaseManager.Read(username);
    }

    //Update user information
    public void Update(String username, User user) {
        dataBaseManager.Update(username, user);
    }

    //Delete user
    public void Delete(User user) {
        dataBaseManager.Delete(user);
    }
}
