package View;

import Model.Order;
import Model.PaymentTransaction;
import Model.Vacation;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class OrderVacation extends AView implements Initializable {
    public TextField Name,ValidDate,cvv,creditCardNname;
    public ImageView payment_img;
    public Button Continue;
    Order order;/*=new Order(controller.getUser(),false);*/
    MessageBoxView msgBoxView;

    String valid, c, credit;
    LocalDate dateNow;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image paymentImage = new Image(this.getClass().getResourceAsStream("/images/Payment-Logo.png"));
        payment_img.setImage(paymentImage);
    }
    public void ContinueToPay(){

        if(ValidDate.getText().equals("") || cvv.getText().equals("") || creditCardNname.equals("")){
            showError();
            return;
        }
        valid=ValidDate.getText().toString();
        c=cvv.getText().toString();
        credit = creditCardNname.getText().toString();
        dateNow = java.time.LocalDate.now();
        setChanged();
        notifyObservers("Ready To Pay");

        PaymentTransaction pay=new PaymentTransaction(order,credit,valid,c,order.getVacation().getPrice(),dateNow);
        msgBoxView.sendPayment();
        setChanged();
        notifyObservers(pay);

        showSent();
        Stage stage = (Stage)Name.getScene().getWindow();
        stage.close();
    }

    private void showSent() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Vacation bought");
        alert.setContentText("Congrats! You bought the vacation - have fun!");
        alert.show();
    }

    private void showError() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Error");
        alert.setContentText("Please Fill in All Details");
        alert.show();
    }

    public void setMessageBoxView(MessageBoxView messageBoxView, Order order) {
        msgBoxView = messageBoxView;
        this.order = order;
    }
}
