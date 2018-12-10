package Model.DataBaseCommunication;

import Model.*;
import javafx.scene.image.Image;

import java.io.File;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;

public class DataBaseManager implements IDataBaseManager{
    private UsersTableManager usersTable;
    private VacationsTableManager vacationsTable;
    private MessagesTableManager messagesTable;
    private OrdersTableManager ordersTable;
    private PaymentsTableManager paymentDetailsTable;
    private UsersPicturesTableManager usersPicturesTable;

    public DataBaseManager(){
        usersTable = new UsersTableManager();
        vacationsTable = new VacationsTableManager();
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

    @Override
    public void CreateUsersProfileImage(String username, File imageFile) {

    }

    @Override
    public Image ReadUsersProfileImage(String username) {
        return null;
    }

    @Override
    public void UpdateUsersProfileImage(String username, File imageFile) {

    }

    @Override
    public void CreateVacation(Vacation vacation) {
        vacationsTable.CreateVacation(vacation);
    }

    @Override
    public List<Vacation> ReadSimilarVacations(Vacation vacation, Comparator<Vacation> vacationsComparator) {
        return vacationsTable.ReadSimilarVacationsNotBought(vacation, vacationsComparator);
    }

    @Override
    public void UpdateVacation(Vacation vacation) {

    }

    @Override
    public void CreateOrder(Order order) {

    }

    @Override
    public List<Order> ReadOrdersForVacation(Vacation vacation) {
        return null;
    }

    @Override
    public void UpdateOrder(Order order) {

    }

    @Override
    public void CreatePaymentTransaction(PaymentTransaction paymentTransaction) {

    }

    @Override
    public void CreateMessage(Message message) {
        messagesTable.Create(message);
    }

    @Override
    public List<Message> ReadAllMessages(String username) {
        return null;
    }

    @Override
    public int CountUnseenMessages(String username) {
        return messagesTable.countUnseenMessages(username);
    }
}
