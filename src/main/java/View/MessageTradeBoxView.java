package View;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MessageTradeBoxView extends AView {
    public Text message;
    public Text from;
    public Button agree_btn, decline_btn, ok_btn;
    private String userFrom;
    private int vacationIDOwn;
    private int vacationIDToTrade;
    public Text details_txt, details_txt_otherUser;

    public void setMessage(String message, String from, int vacationIDOwn, int vacationIDToTrade) {
        this.vacationIDOwn = vacationIDOwn;
        this.vacationIDToTrade = vacationIDToTrade;
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
        Object[] details = new Object[3];
        details[0] = "getVacDetails";
        details[1] = vacationIDOwn;
        details[2] = vacationIDToTrade;
        setChanged();
        notifyObservers(details);
    }


    public void setVacationDetails(String detailsOwn, String detailsToTrade) {
        details_txt.setText(detailsOwn);
        details_txt_otherUser.setText(detailsToTrade);
    }

    public void doAgree(MouseEvent mouseEvent) {
        if(message.getText().contains("wants to trade")){
            String[] strings = new String[3];
            strings[0] = userFrom;
            strings[1] = "vacationTrade";
            strings[2] = String.valueOf(vacationIDOwn);
            strings[3] = String.valueOf(vacationIDToTrade);
            setChanged();
            notifyObservers(strings);
            showAlert("Trade confirmation sent!");
            Stage stage = (Stage)decline_btn.getScene().getWindow();
            stage.close();
        }
    }

    private void showAlert(String text){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(text);
        alert.setTitle("Information");
        alert.show();
    }

    public void doDecline(MouseEvent mouseEvent) {
    }

    public void okButtonClicked(MouseEvent mouseEvent) {
    }
}


