package Model;

import Model.DataBaseCommunication.IDataBaseManager;

import java.sql.SQLException;
import java.util.List;

//Abstract model
public abstract class AModel {
    protected IDataBaseManager dataBaseManager;

    //Create user
    public void CreateUser(User user) throws SQLException {
        dataBaseManager.CreateUser(user);
    }

    //Read similar users
    public List<User> ReadSimilarUsers(String username) {
        return dataBaseManager.ReadSimilarUsers(username);
    }

    //Read user
    public User ReadUser(String username){
        return dataBaseManager.ReadUser(username);
    }

    //Update user information
    public void UpdateUser(String username, User user) {
        dataBaseManager.UpdateUser(username, user);
    }

    //Delete user
    public void DeleteUser(User user) {
        dataBaseManager.DeleteUser(user);
    }

    public void CreateVacation(Vacation vacation){
        dataBaseManager.CreateVacation(vacation);
    }

    List<Vacation> ReadSimilarVacations(Vacation vacation){
        return dataBaseManager.ReadSimilarVacations(vacation);
    }

    void UpdateVacation(Vacation vacation){
        dataBaseManager.UpdateVacation(vacation);
    }

    void CreateOrder(Order order){
        dataBaseManager.CreateOrder(order);
    }

    List<Order> ReadOrdersForVacation(Vacation vacation){
        return dataBaseManager.ReadOrdersForVacation(vacation);
    }

    void UpdateOrder(Order order){
        dataBaseManager.UpdateOrder(order);
    }

    void CreatePaymentTransaction(PaymentTransaction paymentTransaction){
        dataBaseManager.CreatePaymentTransaction(paymentTransaction);
    }

    void CreateMessage(Message message){
        dataBaseManager.CreateMessage(message);
    }

    List<Message> ReadMessages(String username){
        return dataBaseManager.ReadMessages(username);
    }
}
