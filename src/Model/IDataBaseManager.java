package Model;

import java.sql.SQLException;

public interface IDataBaseManager {
    void Create(User user) throws SQLException;
    User Read(String username);
    void Update(String username, User user);
    void Delete(User user);
}
