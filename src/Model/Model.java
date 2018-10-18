package Model;

import java.sql.SQLException;

public class Model {
    DataBaseManager dataBaseManager;

    public Model() {
        dataBaseManager = new DataBaseManager();
    }

    public void Create(User user) {
        try {
            dataBaseManager.Create(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User Read(String username) {
        return dataBaseManager.Read(username);
    }

    public void Update(String username, User user) {
        dataBaseManager.Update(username, user);
    }

    public void Delete(User user) {
        dataBaseManager.Delete(user);
    }
}
