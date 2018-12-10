package Model.DataBaseCommunication;

import Model.*;

import java.sql.SQLException;
import java.util.List;

//Database manager interface
public interface IDataBaseManager {
    void CreateUser(User user) throws SQLException; //Create User
    User ReadUser(String username); //Read user
    List<User> ReadSimilarUsers(String username); //Read user with similar usernames
    void UpdateUser(String username, User user); //Update user information
    void DeleteUser(User user); //Delete User

    void CreateVacation(Vacation vacation); //Create Vacation
    List<Vacation> ReadSimilarVacations(Vacation vacation); //Read vacations with similar parameters
    void UpdateVacation(Vacation vacation); //Update a given vacation in the database

    void CreateOrder(Order order); //Create Order
    List<Order> ReadOrdersForVacation(Vacation vacation);
    void UpdateOrder(Order order);

    void CreatePaymentTransaction(PaymentTransaction paymentTransaction);

    void CreateMessage(Message message);
    List<Message> ReadMessages(String username);
}
