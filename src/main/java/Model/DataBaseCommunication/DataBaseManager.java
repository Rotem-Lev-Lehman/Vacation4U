package Model.DataBaseCommunication;

import Model.User;

import java.sql.SQLException;
import java.util.List;

public class DataBaseManager implements IDataBaseManager{
    private UsersTableManager usersTable;
    private VacationsTableManager vacationsTable;
    private FlightsTableManager flightsTable;
    private MessagesTableManager messagesTable;
    private OrdersTableManager ordersTable;
    private PaymentsTableManager paymentDetailsTable;
    private UsersPicturesTableManager usersPicturesTable;

    public DataBaseManager(){
        usersTable = new UsersTableManager();
        vacationsTable = new VacationsTableManager();
        flightsTable = new FlightsTableManager();
        messagesTable = new MessagesTableManager();
        ordersTable = new OrdersTableManager();
        paymentDetailsTable = new PaymentsTableManager();
        usersPicturesTable = new UsersPicturesTableManager();
    }

    @Override
    public void CreateUser(User user) throws SQLException {
        usersTable.Create(user);
    }

    @Override
    public User ReadUser(String username) {
        return usersTable.Read(username);
    }

    @Override
    public List<User> ReadSimilarUsers(String username) {
        return usersTable.ReadSimilar(username);
    }

    @Override
    public void UpdateUser(String username, User user) {
        usersTable.Update(username, user);
    }

    @Override
    public void DeleteUser(User user) {
        usersTable.Delete(user);
    }
}
