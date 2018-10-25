package View;

import Model.User;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class SignUp1View extends AView implements Initializable {

    public TextField firstName, lastName, city;
    public Text processMessage;
    public DatePicker date;
    public Button continueBtn;
    private boolean pressedContinue;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pressedContinue = false;

        if(!controller.isUserNull()){
            User user = controller.getUser();
            firstName.setText(user.getFirstName());
            lastName.setText(user.getLastName());
            city.setText(user.getCity());
            date.setValue(LocalDate.parse(user.getBirthdate()));
            //date.setValue(new LocalDate(DateTimeFormatter.ofPattern(user.getBirthdate())));
        }

        Stage currentStage = (Stage) continueBtn.getScene().getWindow();
        currentStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                if(!pressedContinue)
                    controller.setUser(null);
            }
        });
    }

    public void ctnClicked(MouseEvent mouseEvent) {
        String firstNameText = firstName.getText();
        String lastNameText = lastName.getText();
        LocalDate localDate = date.getValue();
        String cityText = city.getText();

        Object[] objects = new Object[4];
        objects[0] = firstNameText;
        objects[1] = lastNameText;
        objects[2] = localDate;
        objects[3] = cityText;

        setChanged();
        notifyObservers(objects);
    }

    public void setMessageFillDetails(){
        processMessage.setText("Please fill all details above");
    }

    public void setMessageLegalAge(){
        processMessage.setText("You must be above 18");
    }

    public void successfullySignedUp(){
        processMessage.setText("");
        pressedContinue = true;
        moveToNewScreen(400, 395, "SignUp2.fxml", "Register");
        Stage currentStage = (Stage) continueBtn.getScene().getWindow();
        currentStage.close();
    }

    /*private boolean legalAge2() {
        LocalDate dateNow = java.time.LocalDate.now();
        System.out.println(YEARS.between(date.getValue(), dateNow));
        long differnce = YEARS.between(dateNow, date.getValue());
        System.out.println(differnce >= 18);
        return YEARS.between(dateNow, date.getValue()) >= 18;
    }*/

}
