package Model.DataBaseCommunication;

import Model.User;

import java.sql.SQLException;
import java.util.List;

public class DataBaseManager implements IDataBaseManager{
    private ATableManager usersTable;
    private ATableManager vacationsTable;
    private ATableManager flightsTable;
    private ATableManager messagesTable;
    private ATableManager ordersTable;
    private ATableManager paymentDetailsTable;
    private ATableManager usersPicturesTable;

    public DataBaseManager(){
        usersTable = new UsersTableManager();
        vacationsTable = new VacationsTableManager();
        flightsTable = new FlightsTableManager();
        messagesTable = new MessagesTableManager();
        ordersTable = new OrdersTableManager();
        paymentDetailsTable = new PaymentsTableManager();
        usersPicturesTable = new UsersTableManager();
    }

    @Override
    public void Create(User user) throws SQLException {

    }

    @Override
    public User Read(String username) {
        return null;
    }

    @Override
    public List<User> ReadSimilar(String username) {
        return null;
    }

    @Override
    public void Update(String username, User user) {

    }

    @Override
    public void Delete(User user) {

    }
}
