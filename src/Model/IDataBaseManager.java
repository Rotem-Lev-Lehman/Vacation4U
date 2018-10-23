package Model;

import java.sql.SQLException;
import java.util.List;

public interface IDataBaseManager {
    void Create(User user) throws SQLException;
    List<User> Read(String username);
    void Update(String username, User user);
    void Delete(User user);
}
