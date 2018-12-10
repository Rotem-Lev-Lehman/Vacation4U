package View;

import Model.Order;
import Model.PaymentTransaction;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class OrderVacation extends AView implements Initializable {
    public TextField Name,ValidDate,cvv,creditCardNname;
    public ImageView payment_img;
    public Button Continue;
    Order order;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image paymentImage = new Image(this.getClass().getResourceAsStream("/images/Payment-Logo.pnp"));
        payment_img.setImage(paymentImage);
    }
    public void ContinueToPay(){
        String valid=ValidDate.getText().toString();
        String c=cvv.getText().toString();
        String credit = creditCardNname.getText().toString();
        LocalDate dateNow = java.time.LocalDate.now();
        PaymentTransaction pay=new PaymentTransaction(order,credit,valid,c,order.getVacation().getPrice(),dateNow);
    }

}
