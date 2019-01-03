package View;

import Model.Order;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class MessageBoxView extends AView {
    public Text message;
    public Text from;
    public TextField phone_number_txtField;
    public Button agree_btn, decline_btn, confirm_phone_btn, ok_btn;
    private String userFrom;
    private int vacationID;
    public Text vac_details_txt, details_txt;

    public void setMessage(String message, String from, int vacationID) {
        this.vacationID = vacationID;
        userFrom = from;
        this.message.setText(message);
        this.from.setText("~ From: " + from);
        if((message.split(" ")[0]).equals("Hey!")){
            agree_btn.setVisible(true);
            decline_btn.setVisible(true);
        }
        else if(message.split(" ")[0].equals("Hello!") || message.split(" ")[0].equals("Yay!") || message.split(" ")[0].equals("Sorry,"))
            ok_btn.setVisible(true);

        //Get the vacations details
        Object[] details = new Object[2];
        details[0] = "getVacDetails";
        details[1] = vacationID;
        setChanged();
        notifyObservers(details);

    }


    public void doAgree(MouseEvent mouseEvent) {
        if(message.getText().contains("wants to buy")){
            String[] strings = new String[3];
            strings[0] = userFrom;
            strings[1] = "buyMessage";
            strings[2] = String.valueOf(vacationID);
            EnterPhoneNumberMode();
            /*setChanged();
            notifyObservers(strings);

            showAlert("Message was sent to " + userFrom + " - order request approved");*/
        }
        else if(message.getText().contains("you have payed")){
            String[] strings = new String[3];
            strings[0] = userFrom;
            strings[1] = "buyApproved";
            strings[2] = String.valueOf(vacationID);
            setChanged();
            notifyObservers(strings);
            //moveToPayment();
            //moveToNewScreen(210,330,"OrderVacation.fxml","Order");
            showAlert("Message was sent to " + userFrom + " - payment request approved");
            Stage stage = (Stage)decline_btn.getScene().getWindow();
            stage.close();
        }
        else if(message.getText().contains("have payed") && message.getText().contains("user")){
            String[] strings = new String[3];
            strings[0] = userFrom;
            strings[1] = "vacationBought";
            strings[2] = String.valueOf(vacationID);
            setChanged();
            notifyObservers(strings);
            showAlert("Congratulation - Your vacation was bought");
            Stage stage = (Stage)decline_btn.getScene().getWindow();
            stage.close();
        }

    }

    private void EnterPhoneNumberMode() {
        decline_btn.setVisible(false);
        agree_btn.setVisible(false);
        confirm_phone_btn.setVisible(true);
        phone_number_txtField.setVisible(true);
    }

    public void moveToPayment(Order order) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = null;
        try {
            root = fxmlLoader.load(getClass().getResource("/OrderVacation.fxml").openStream());
        } catch (IOException e) {
            System.out.println(e.toString());
        }

        AView view = fxmlLoader.getController();
        view.setController(controller);
        ((OrderVacation)fxmlLoader.getController()).setMessageBoxView(this, order);

        Stage stage = new Stage();
        stage.setTitle("Message");
        stage.setScene(new Scene(root, 210, 320));
        stage.show();

        view.setDefaults(stage);
    }

    public void doDecline(MouseEvent mouseEvent) {
        String add = "";
        if(message.getText().contains("wants to buy")){
            String[] strings = new String[3];
            strings[0] = userFrom;
            strings[1] = "declineBuyMessage";
            strings[2] = String.valueOf(vacationID);
            setChanged();
            notifyObservers(strings);
            add = "Order";
        }
        else if(message.getText().contains("you have payed")){
            String[] strings = new String[3];
            strings[0] = userFrom;
            strings[1] = "buyDeclined";
            strings[2] = String.valueOf(vacationID);
            setChanged();
            notifyObservers(strings);
            add = "Payment";
        }
        else if(message.getText().contains("have payed") && message.getText().contains("user")){
            String[] strings = new String[3];
            strings[0] = userFrom;
            strings[1] = "publisherBuyDeclined";
            strings[2] = String.valueOf(vacationID);
            setChanged();
            notifyObservers(strings);
            add = "Payment";
        }
        showAlert("Message was sent to " + userFrom + " - " + add + " request was declined");
        Stage stage = (Stage)decline_btn.getScene().getWindow();
        stage.close();
    }

    private void showAlert(String text){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(text);
        alert.setTitle("Information");
        alert.show();
    }

    public void sendPayment() {
        String[] strings = new String[3];
        strings[0] = userFrom;
        strings[1] = "buyApproved";
        strings[2] = String.valueOf(vacationID);
        setChanged();
        notifyObservers(strings);
    }

    public void sendPhoneMessage(MouseEvent mouseEvent) {
        String phone = phone_number_txtField.getText();
        if(phone == null || phone.equals(""))
            showAlert("Please enter your phone number");
        else if(!isPhoneNumber(phone))
            showAlert("Please enter phone number in correct format");
        else{
            String[] strings = new String[3];
            strings[0] = userFrom;
            strings[1] = "buyMessage;" + phone;
            strings[2] = String.valueOf(vacationID);
            setChanged();
            notifyObservers(strings);

            showAlert("Message was sent to " + userFrom + " - order request approved");

            Stage stage = (Stage)decline_btn.getScene().getWindow();
            stage.close();
        }
    }

    private boolean isPhoneNumber(String phone) {
        for(int i = 0; i < phone.length(); i++){
            if(phone.charAt(i) != '-' && !Character.isDigit(phone.charAt(i)))
                return false;
        }
        return true;
    }

    public void okButtonClicked(MouseEvent mouseEvent) {
        if(message.getText().split(" ")[0].equals("Sorry,") || message.getText().split(" ")[0].equals("Yay!")) {
            Stage stage = (Stage)decline_btn.getScene().getWindow();
            stage.close();
            return;
        }
        String[] strings = new String[3];
        strings[0] = userFrom;
        strings[1] = "contactApproved";
        strings[2] = String.valueOf(vacationID);
        setChanged();
        notifyObservers(strings);

        Stage stage = (Stage)decline_btn.getScene().getWindow();
        stage.close();
    }

    public void setVacationDeatils(String details){
        vac_details_txt.setText("Vacation Details: ");
        details_txt.setText(details);
    }
}
