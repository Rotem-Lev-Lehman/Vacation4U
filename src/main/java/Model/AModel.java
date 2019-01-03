package Model;

import Model.DataBaseCommunication.IDataBaseManager;
import javafx.scene.image.Image;

import java.io.File;
import java.sql.SQLException;
import java.util.Comparator;
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

    public void CreateUsersProfileImage(String username, File imageFile){
        dataBaseManager.CreateUsersProfileImage(username,imageFile);
    }

    public Image ReadUsersProfileImage(String username){
        return dataBaseManager.ReadUsersProfileImage(username);
    }

    public void UpdateUsersProfileImage(String username, File imageFile){
        dataBaseManager.UpdateUsersProfileImage(username, imageFile);
    }

    public void CreateVacation(Vacation vacation){
        dataBaseManager.CreateVacation(vacation);
    }

    public Vacation ReadVacation(int vacationID){
        return dataBaseManager.ReadVacation(vacationID);
    }

    public List<Vacation> ReadSimilarVacations(Vacation vacation, Comparator<Vacation> vacationComparator){
        return dataBaseManager.ReadSimilarVacations(vacation, vacationComparator);
    }

    public void UpdateVacation(Vacation vacation){
        dataBaseManager.UpdateVacation(vacation);
    }

    public void CreateOrder(Order order){
        dataBaseManager.CreateOrder(order);
    }

    public Order ReadOrder(Vacation vacation, User buyer) {
        return dataBaseManager.ReadOrder(vacation, buyer);
    }

    public List<Order> ReadOrdersForVacation(Vacation vacation){
        return dataBaseManager.ReadOrdersForVacation(vacation);
    }

    public void UpdateOrder(Order order){
        //dataBaseManager.UpdateOrder(order);
    }

    public void CreatePaymentTransaction(PaymentTransaction paymentTransaction){
        dataBaseManager.CreatePaymentTransaction(paymentTransaction);
    }

    public void CreateMessage(Message message){
        dataBaseManager.CreateMessage(message);
    }

    public List<Message> ReadAllMessages(String username){
        return dataBaseManager.ReadAllMessages(username);
    }
    public int CountUnseenMessages(String username){
        return dataBaseManager.CountUnseenMessages(username);
    }

    public void UpdateMessageAsSeen(Message message){dataBaseManager.UpdateMessageAsSeen(message);}

    public boolean checkHasVacation(String username){ return dataBaseManager.checkHasVacation(username);}
}
