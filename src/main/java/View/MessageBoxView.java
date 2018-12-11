package View;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MessageBoxView extends AView {
    public Text message;
    public Text from;
    public Button agree_btn, decline_btn;
    private String userFrom;
    private int vacationID;

    public void setMessage(String message, String from, int vacationID) {
        this.vacationID = vacationID;
        userFrom = from;
        this.message.setText(message);
        this.from.setText("~ From: " + from);
        if((message.split(" ")[0]).equals("Hey!")){
            agree_btn.setVisible(true);
            decline_btn.setVisible(true);
        }
    }


    public void doAgree(MouseEvent mouseEvent) {
        if(message.getText().contains("wants to buy")){
            String[] strings = new String[3];
            strings[0] = userFrom;
            strings[1] = "buyMessage";
            strings[2] = String.valueOf(vacationID);
            setChanged();
            notifyObservers(strings);

            showAlert("Message was sent to " + userFrom + " - order request approved");
        }
        else if(message.getText().contains("approved")){
            /*String[] strings = new String[2];
            strings[0] = userFrom;
            strings[1] = "buyApproved";
            setChanged();
            notifyObservers(strings);*/
            moveToNewScreen(210,330,"OrderVacation.fxml","Order");
        }
        Stage stage = (Stage)decline_btn.getScene().getWindow();
        stage.close();
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
        else if(message.getText().contains("approved")){
            String[] strings = new String[3];
            strings[0] = userFrom;
            strings[1] = "buyDeclined";
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
}
