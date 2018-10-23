package Model;

import java.sql.SQLException;
import java.util.List;

public abstract class AModel {
    protected IDataBaseManager dataBaseManager;

    public void Create(User user) throws SQLException {
        dataBaseManager.Create(user);
    }

    public List<User> Read(String username) {
        return dataBaseManager.Read(username);
    }

    public void Update(String username, User user) {
        dataBaseManager.Update(username, user);
    }

    public void Delete(User user) {
        dataBaseManager.Delete(user);
    }
}
