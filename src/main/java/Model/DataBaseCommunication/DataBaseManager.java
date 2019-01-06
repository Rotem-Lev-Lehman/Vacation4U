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
    private UsersPicturesTableManager usersPicturesTable;
    private Object lock;

    public DataBaseManager(){
        usersTable = new UsersTableManager();
        vacationsTable = new VacationsTableManager();
        messagesTable = new MessagesTableManager();
        ordersTable = new OrdersTableManager();
        usersPicturesTable = new UsersPicturesTableManager();
        lock = new Object();
    }

    @Override
    public void CreateUser(User user) throws SQLException {
        synchronized (lock) {
            usersTable.Create(user);
        }
    }

    @Override
    public User ReadUser(String username) {
        User user;
        synchronized (lock) {
            user = usersTable.Read(username);
        }
        return user;
    }

    @Override
    public List<User> ReadSimilarUsers(String username) {
        List<User> users;
        synchronized (lock) {
            users = usersTable.ReadSimilar(username);
        }
        return users;
    }

    @Override
    public void UpdateUser(String username, User user) {
        synchronized (lock) {
            usersTable.Update(username, user);
        }
    }

    @Override
    public void DeleteUser(User user) {
        synchronized (lock) {
            usersTable.Delete(user);
        }
    }

    @Override
    public void CreateUsersProfileImage(String username, File imageFile) { //test
        synchronized (lock) {
            usersPicturesTable.CreateUsersProfileImage(username, imageFile);
        }
    }

    @Override
    public Image ReadUsersProfileImage(String username) { //test
        Image image;
        synchronized (lock) {
            image = usersPicturesTable.ReadUsersProfileImage(username);
        }
        return image;
    }

    @Override
    public void UpdateUsersProfileImage(String username, File imageFile) { //test
        synchronized (lock) {
            usersPicturesTable.UpdateUsersProfileImage(username, imageFile);
        }
    }

    @Override
    public void CreateVacation(Vacation vacation) {
        synchronized (lock) {
            vacationsTable.CreateVacation(vacation);
        }
    }

    @Override
    public Vacation ReadVacation(int vacationID) {
        Vacation vacation;
        synchronized (lock){
            vacation = vacationsTable.Read(vacationID);
        }
        return vacation;
    }

    @Override
    public List<Vacation> ReadSimilarVacations(Vacation vacation, Comparator<Vacation> vacationsComparator) {
        List<Vacation> vacations;
        synchronized (lock) {
            vacations = vacationsTable.ReadSimilarVacationsNotBought(vacation, vacationsComparator);
        }
        return vacations;
    }

    @Override
    public void UpdateVacation(Vacation vacation) { //test
        synchronized (lock) {
            vacationsTable.UpdateVacation(vacation);
        }
    }

    @Override
    public void CreateOrder(Order order) {
        synchronized (lock) {
            ordersTable.CreateOrder(order);
        }
    }

    @Override
    public Order ReadOrder(Vacation vacation, User buyer) {
        Order order;
        synchronized (lock){
            order = ordersTable.Read(vacation, buyer);
        }
        return order;
    }

    @Override
    public List<Order> ReadOrdersForVacation(Vacation vacation) {
        List<Order> orders;
        synchronized (lock) {
            orders = ordersTable.ReadOrdersForVacation(vacation);
        }
        return orders;
    }

    @Override
    public void UpdateOrderStatus(Order order) {
        synchronized (lock) {
            ordersTable.UpdateOrderStatus(order);
        }
    }

    @Override
    public void CreateMessage(Message message) {
        synchronized (lock) {
            messagesTable.Create(message);
        }
    }

    @Override
    public List<Message> ReadAllMessages(String username) {
        List<Message> messages;
        synchronized (lock) {
            messages = messagesTable.ReadAllMessages(username);
        }
        return messages;
    }

    @Override
    public int CountUnseenMessages(String username) {
        int count;
        synchronized (lock) {
            count = messagesTable.countUnseenMessages(username);
        }
        return count;
    }

    @Override
    public void UpdateMessageAsSeen(Message message) {
        synchronized (lock) {
            messagesTable.UpdateAsSeen(message);
        }
    }

    @Override
    public int CountVacationsByUserID(String username){
        int count;
        synchronized (lock){
            count = vacationsTable.CountVacationsByUserID(username);
        }
        return count;
    }

    @Override
    public boolean checkHasVacation(String username) {
        boolean hasVacation = false;
        synchronized (lock){
            int count = vacationsTable.CountVacationsByUserID(username);
            if(count > 0)
                hasVacation = true;
        }
        return hasVacation;
    }

    @Override
    public List<Vacation> ReadVacationsByUserID(String username) {
        List<Vacation> vacations;
        synchronized (lock){
            vacations = vacationsTable.ReadVacationsByUserID(username);
        }
        return vacations;
    }
}